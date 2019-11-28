package com.api.org.openapitools.api;

import com.api.org.openapitools.model.Error;
import com.api.org.openapitools.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/student")
@Api(description = "the student API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-11-28T19:11:58.402472300+01:00[Europe/Berlin]")
public class StudentApi {

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "returns student information by id", notes = "returns student information by id", response = Student.class, responseContainer = "List", tags={ "admins", "guests",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Student.class, responseContainer = "List"),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class)
    })
    public Response getStudentById(@PathParam("id") Integer id) {
        Student student = new Student();
        student.setName("test");
        student.setAge(21);
        student.setEnrolmentNumber(744317);
        student.setFieldOfStudy("Computer Science");
        student.setId(UUID.fromString(student.getName()));

        return Response.ok().entity("magic!").build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Partial update of student information", notes = "Partial update of student information", response = Void.class, tags={ "admins",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "item updated", response = Void.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid", response = Void.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class)
    })
    public Response partialUpdateStudent(@PathParam("id") Integer id,@Valid Student student) {
        return Response.ok().entity("magic!").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Deletion of a student in the system", notes = "Deletion of a student in the system", response = Void.class, tags={ "admins",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "student deleted", response = Void.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class)
    })
    public Response studentDeletion(@PathParam("id") Integer id) {
        return Response.ok().entity("magic!").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Updates a student in the system", notes = "Updates a student in the system", response = Void.class, tags={ "admins" })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "item updated", response = Void.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid", response = Void.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class)
    })
    public Response updateStudent(@PathParam("id") Integer id,@Valid Student student) {
        return Response.ok().entity("magic!").build();
    }
}
