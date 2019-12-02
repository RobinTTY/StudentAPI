package com.api.org.openapitools.api;

import com.api.org.openapitools.model.Error;
import com.api.org.openapitools.model.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.Document;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

@Path("/students")
@Api(description = "the students API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-12-02T10:02:44.649+01:00[Europe/Berlin]")
public class StudentsApi {

    @POST
    @Consumes({ "application/json", "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Adds a new student to the system", notes = "", response = Student.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Ressource created", response = Student.class),
        @ApiResponse(code = 409, message = "An existing item already exists", response = Void.class)
    })
    public Response addStudent(@Valid Student student) {
        // get max student id
        Student maxIdStudent = (Student)RestApplication.studentCollection.find().sort(new BasicDBObject("_id", -1)).first();

        // set id, uuid automatically
        student.setId(maxIdStudent.getId() + 1);
        student.setUuid(UUID.randomUUID());

        // add to database
        RestApplication.studentCollection.insertOne(student);
        return Response.ok(student, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "returns student information by id", notes = "", response = Student.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Search result matching criteria", response = Student.class, responseContainer = "List"),
        @ApiResponse(code = 200, message = "Unexpected error", response = Error.class)
    })
    public Response getStudentById(@PathParam("id") Integer id) {
        Student stud = (Student)RestApplication.studentCollection.find(Filters.eq("_id", id)).first();
        if(stud != null)
            return Response.ok(stud, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Lists all students currently enrolled", notes = "", response = Student.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation successful", response = Student.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal Server error", response = Void.class)
    })
    public Response listStudents() {
        try{
            List<Object> studList = new ArrayList<>();
            MongoCursor<Student> cursor = RestApplication.studentCollection.find().iterator();
            while(cursor.hasNext()){
                Student stud = (Student)cursor.next();
                studList.add(stud);
            }

            return Response.ok(studList, MediaType.APPLICATION_JSON).build();
        }catch (Exception e){
            System.out.println(e);
            return Response.serverError().build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Partial update of student information", notes = "", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Item updated", response = Void.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid", response = Void.class),
        @ApiResponse(code = 200, message = "Unexpected error", response = Error.class)
    })
    public Response partialUpdateStudent(@PathParam("id") Integer id,Student student) {
        return internalUpdateStudent(id, student);
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Deletion of a student in the system", notes = "", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Student deleted", response = Void.class),
        @ApiResponse(code = 200, message = "Unexpected error", response = Error.class)
    })
    public Response studentDeletion(@PathParam("id") Integer id) {
        Student queryResult = (Student)RestApplication.studentCollection.find(Filters.eq("_id", id)).first();
        if(queryResult == null)
            return Response.noContent().build();

        DeleteResult deleteResult = RestApplication.studentCollection.deleteOne(new Document("_id", queryResult.getId()));
        if(deleteResult.wasAcknowledged())
            return Response.ok().entity("Entity removed.").build();
        else
            return Response.noContent().entity("No matching entity").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Updates a student in the system", notes = "", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Item updated", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid input, object invalid", response = Void.class),
        @ApiResponse(code = 200, message = "Unexpected error", response = Error.class)
    })
    public Response updateStudent(@PathParam("id") Integer id,@Valid Student student) {
        return internalUpdateStudent(id, student);
    }

    // method doesn't care about partial or full update, could extract more helper methods here to generalize for more put/patch methods
    private Response internalUpdateStudent(@PathParam("id") Integer id, Student student) {
        Student stud = (Student) RestApplication.studentCollection.find(Filters.eq("_id", id)).first();
        Map<String, Object> updateMap = new HashMap<>();

        if(stud == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        // reflection magic, update values of Student but ignore uuid/id since they are set automatically
        try{
            for (PropertyDescriptor pd : Introspector.getBeanInfo(Student.class).getPropertyDescriptors()) {
                if(!pd.getName().equals("uuid") && !pd.getName().equals("id"))
                    if (pd.getReadMethod() != null && !"class".equals(pd.getName())){
                        Object value = pd.getReadMethod().invoke(student);
                        if(value != null)
                            updateMap.put(pd.getName(), value);
                    }
            }
        }catch(Exception e){
            return Response.serverError().entity("Internal Server error.").build();
        }

        UpdateResult result = RestApplication.studentCollection.updateOne(Filters.eq("_id", id), new Document("$set", new Document(updateMap)));
        stud = (Student)RestApplication.studentCollection.find(Filters.eq("_id", id)).first();
        return Response.ok(stud, MediaType.APPLICATION_JSON).build();
    }
}
