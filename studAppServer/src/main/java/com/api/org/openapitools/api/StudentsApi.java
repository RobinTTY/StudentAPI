package com.api.org.openapitools.api;

import com.api.org.openapitools.model.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/students")
@Api(description = "the students API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2019-11-30T22:36:46.177+01:00[Europe/Berlin]")
public class StudentsApi {

    @POST
    @Consumes({ "application/json", "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Adds a new student to the system", notes = "", response = Student.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Ressource created", response = Student.class),
        @ApiResponse(code = 409, message = "This item already exists", response = Void.class)
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
}
