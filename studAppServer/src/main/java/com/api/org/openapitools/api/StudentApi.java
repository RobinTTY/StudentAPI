package com.api.org.openapitools.api;

import com.api.org.openapitools.model.Error;
import com.api.org.openapitools.model.Student;
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
import java.util.HashMap;
import java.util.Map;

@Path("/student")
@Api(description = "the student API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-11-30T22:36:46.177+01:00[Europe/Berlin]")
public class StudentApi {

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "returns student information by id", notes = "", response = Student.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Search result matching criteria", response = Student.class, responseContainer = "List"),
        @ApiResponse(code = 204, message = "No content", response = Error.class)
    })
    public Response getStudentById(@PathParam("id") Integer id) {
        Student stud = (Student)RestApplication.studentCollection.find(Filters.eq("_id", id)).first();
        if(stud != null)
            return Response.ok(stud, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Partial update of student information", notes = "", response = Void.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Item updated", response = Void.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid", response = Void.class),
        @ApiResponse(code = 500, message = "Internal Server error", response = Error.class)
    })
    public Response partialUpdateStudent(@PathParam("id") Integer id, Student student) {
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
        @ApiResponse(code = 500, message = "Internal Server error", response = Error.class)
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
