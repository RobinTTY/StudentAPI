package com.api.org.openapitools.api;

import com.api.org.openapitools.db.StudentDatabase;
import com.api.org.openapitools.model.Student;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.transformation.SortedList;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.*;

@ApplicationPath("/v1")
public class RestApplication extends Application {
    public static MongoCollection studentCollection;

    public RestApplication() {
        StudentDatabase db = new StudentDatabase();
        studentCollection = db.getStudentDatabase().getCollection("Students", Student.class);
        //db.GenerateDataSet();
    }
}
