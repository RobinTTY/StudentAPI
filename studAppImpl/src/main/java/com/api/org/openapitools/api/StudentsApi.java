package com.api.org.openapitools.api;

import com.api.org.openapitools.model.Student;
import com.mongodb.Block;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/students")
@Api(description = "the students API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-11-28T19:11:58.402472300+01:00[Europe/Berlin]")
public class StudentsApi {

    @POST
    @Produces({ "application/json" })
    @ApiOperation(value = "Adds a new student to the system", notes = "Adds a new student to the system", response = Student.class, tags={ "admins",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Ressource created", response = Student.class),
        @ApiResponse(code = 409, message = "an existing item already exists", response = Void.class)
    })
    public Response addStudent() {
        RestApplication.studentCollection.

        return Response.ok().entity("magic!").build();
    }

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "lists all students", notes = "lists all students currently enrolled ", response = Student.class, responseContainer = "List", tags={ "admins", "guests" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "operation successful", response = Student.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal Server error", response = Void.class)
    })
    public Response listStudents() {
        ArrayList<Object> studList = new ArrayList<Object>();
        MongoCursor<Document> cursor = RestApplication.studentCollection.find().iterator();
        while(cursor.hasNext()){
            Document doc = cursor.next();
            studList.add(doc.values());
        }

        return Response.ok().entity(studList).build();
    }

    private void addToCollection(){

    };
}
