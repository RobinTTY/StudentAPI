package com.api.org.openapitools.db;

import com.api.org.openapitools.model.Student;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.UUID;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class StudentDatabase {
    private MongoDatabase studentDatabase;

    public StudentDatabase() {
        try {
            ConnectDatabase();
        }catch(Exception e){
            System.out.println("Database connection failed!");
            System.out.println(e);
        }
    }

    // used to generate student dataset in MongoDB
    public void GenerateDataSet(){
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student(UUID.randomUUID(), 1, "Sally Whelan", 21, 737852, "Computer Science"));
        studentList.add(new Student(UUID.randomUUID(), 2, "Ethel Livingston", 23, 742914, "Mathematics"));
        studentList.add(new Student(UUID.randomUUID(), 3, "Mariyam Golden", 19, 791524, "Physics"));
        studentList.add(new Student(UUID.randomUUID(), 4, "Jayson Cobb", 24, 756846, "Bio-Engineering"));
        studentList.add(new Student(UUID.randomUUID(), 5, "Samirah Quintana", 29, 733581, "Planetary Science"));

        studentList.forEach(student -> {
            studentDatabase.getCollection("Students", Student.class).insertOne(student);
        });
    }

    public MongoDatabase getStudentDatabase() {
        return studentDatabase;
    }

    private void ConnectDatabase(){
        // yes this is not secure ;)
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://Robin:yQjPtQ4JzbcTUC9Rzctx@cluster0-qdzqz.mongodb.net/test?retryWrites=true&w=majority");

        // this is needed to be able to translate bson documents to and from POJO
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient(uri);
        studentDatabase = mongoClient.getDatabase("StudentDB").withCodecRegistry(pojoCodecRegistry);
    }
}
