package com.api.org.openapitools.api;

import com.api.org.openapitools.model.Student;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/students")
@Api(description = "the students API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-11-29T08:07:26.085586100+01:00[Europe/Berlin]")
public class StudentsApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Adds a new student to the system", notes = "Adds a new student to the system", response = Student.class, tags={ "admins", "guests"  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Ressource created", response = Student.class),
        @ApiResponse(code = 409, message = "an existing item already exists", response = Void.class)
    })
    public Response addStudent(@Valid Student student) {
        Object maxStudId = RestApplication.studentCollection.find().sort(new BasicDBObject("id", -1)).first();
        System.out.println(maxStudId.toString());
        System.out.println(maxStudId);
        // we need to be able to cast this object to Student

        //student.setId(maxStudId.getId() + 1);
        //student.setUuid(UUID.randomUUID());
        //RestApplication.studentCollection.insertOne(student);
        return Response.ok(student, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "lists all students", notes = "lists all students currently enrolled ", response = Student.class, responseContainer = "List", tags={ "admins", "guests" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation successful", response = Student.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal Server error", response = Void.class)
    })
    public Response listStudents() {
        try{
            List<Object> studList = new ArrayList<>();
            MongoCursor<Document> cursor = RestApplication.studentCollection.find().iterator();
            while(cursor.hasNext()){
                Document doc = cursor.next();
                studList.add(doc);
            }

            return Response.ok(studList, MediaType.APPLICATION_JSON).build();
        }catch (Exception e){
            System.out.println(e);
            return Response.serverError().build();
        }
    }
}
