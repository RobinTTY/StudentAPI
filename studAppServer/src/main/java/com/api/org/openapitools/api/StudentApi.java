package com.api.org.openapitools.api;

import com.api.org.openapitools.model.Student;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.Document;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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
        @ApiResponse(code = 200, message = "Unexpected error", response = Error.class)
    })
    public Response getStudentById(@PathParam("id") Integer id) {
        Object stud = RestApplication.studentCollection.find(Filters.eq("id", id));

        if(stud != null)
            return Response.ok().entity(stud).build();
        else
            return Response.status(204).entity("No matching database entry found.").build();
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
    public Response partialUpdateStudent(@PathParam("id") Integer id,@Valid Student student) {
        return Response.ok().entity("magic!").build();
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
        Student queryResult = (Student)RestApplication.studentCollection.find(Filters.eq("id", id)).first();
        DeleteResult deleteResult = RestApplication.studentCollection.deleteOne(new Document("id", queryResult.getId()));

        if(deleteResult.wasAcknowledged())
            return Response.ok().entity("magic!").build();
        else
            return Response.noContent().build();
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
        return Response.ok().entity("magic!").build();
    }
}
