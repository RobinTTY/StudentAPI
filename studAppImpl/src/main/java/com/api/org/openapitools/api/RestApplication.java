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
        studentCollection = db.getStudentDatabase().getCollection("Students");
        //GenerateDataSet();
    }

    // used to generate student dataset in MongoDB
    private void GenerateDataSet(){
        Student stud1 = new Student();
        stud1.setUuid(UUID.randomUUID());
        stud1.setId(1);
        stud1.setName("Sally Whelan");
        stud1.setAge(21);
        stud1.setEnrolmentNumber(737852);
        stud1.setFieldOfStudy("Computer Science");

        Student stud2 = new Student();
        stud2.setUuid(UUID.randomUUID());
        stud2.setId(2);
        stud2.setName("Ethel Livingston");
        stud2.setAge(23);
        stud2.setEnrolmentNumber(742914);
        stud2.setFieldOfStudy("Mathematics");

        Student stud3 = new Student();
        stud3.setUuid(UUID.randomUUID());
        stud3.setId(3);
        stud3.setName("Mariyam Golden");
        stud3.setAge(19);
        stud3.setEnrolmentNumber(791524);
        stud3.setFieldOfStudy("Physics");

        Student stud4 = new Student();
        stud4.setUuid(UUID.randomUUID());
        stud4.setId(4);
        stud4.setName("Jayson Cobb");
        stud4.setAge(24);
        stud4.setEnrolmentNumber(756846);
        stud4.setFieldOfStudy("Bio-Engineering");

        Student stud5 = new Student();
        stud5.setUuid(UUID.randomUUID());
        stud5.setId(5);
        stud5.setName("Samirah Quintana");
        stud5.setAge(29);
        stud5.setEnrolmentNumber(733581);
        stud5.setFieldOfStudy("Planetary Science");

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(stud1);
        studentList.add(stud2);
        studentList.add(stud3);
        studentList.add(stud4);
        studentList.add(stud5);

        studentList.forEach(student -> {
            studentCollection.insertOne(new Document("_id", student.getUuid())
                    .append("id", student.getId())
                    .append("name", student.getName())
                    .append("age", student.getAge())
                    .append("enrollmentNumber", student.getEnrolmentNumber())
                    .append("fieldOfStudy", student.getFieldOfStudy())
            );
        });
    }
}
