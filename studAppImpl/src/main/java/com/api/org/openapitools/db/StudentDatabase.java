package com.api.org.openapitools.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

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

    public MongoDatabase getStudentDatabase() {
        return studentDatabase;
    }

    private void ConnectDatabase(){
        // yes this is not secure ;)
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://Robin:yQjPtQ4JzbcTUC9Rzctx@cluster0-qdzqz.mongodb.net/test?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        studentDatabase = mongoClient.getDatabase("StudentDB");
    }
}
