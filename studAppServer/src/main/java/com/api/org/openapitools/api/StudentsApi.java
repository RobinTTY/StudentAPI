package com.api.org.openapitools.api;

import com.api.org.openapitools.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.Document;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Object maxStudId = RestApplication.studentCollection.find().sort(new BasicDBObject("id", -1)).first();

        // serialize student object
        ObjectMapper mapper = new ObjectMapper();
        try {
            Student stud = mapper.readValue(maxStudId.toString(), Student.class);
            System.out.println("id:" + stud.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // we need to be able to cast this object to Student

        //student.setId(maxStudId.getId() + 1);
        //student.setUuid(UUID.randomUUID());
        //RestApplication.studentCollection.insertOne(student);
        return Response.ok(maxStudId, MediaType.APPLICATION_JSON).build();
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
