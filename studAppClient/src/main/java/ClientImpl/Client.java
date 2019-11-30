package ClientImpl;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.AdminsApi;
import org.openapitools.client.model.Student;

import java.util.UUID;

public class Client {
    public static void main(String[] args) {
        try {
            DoSomething();
        }catch (ApiException e){
            System.out.println(e);
        }
    }

    public static void DoSomething() throws ApiException {
        AdminsApi client = new AdminsApi();

        Student exampleStudent = new Student();
        exampleStudent.uuid(UUID.randomUUID());
        exampleStudent.id(120);
        exampleStudent.age(19);
        exampleStudent.enrolmentNumber(773549);
        exampleStudent.fieldOfStudy("Electrical Engineering");
        exampleStudent.name("Ervin Mckenzie");
        client.addStudent(exampleStudent);
    }
}
