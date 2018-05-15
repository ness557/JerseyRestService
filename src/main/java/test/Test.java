package test;

import model.Student;
import repository.*;

public class Test {

    public static void main(String[] args) {
        new Test().start();
    }

    public void start(){

//        JdbcConnector connector = new JdbcConnector();

        StudentRepository repository = new StudentJdbcRepository();

//        repository.addStudent(new Student(1, "Ness", "Ki144"));
//        repository.addStudent(new Student(2, "sss", "sssssss"));
//        repository.addStudent(new Student(3, "dfsfs", "dsfsdf"));

        StudentSpecification specification = new StudentSpecificationAll();

        for (Student st: repository.query(specification)) {

            System.out.println(st);
        }

        System.out.println("\n\n\n");

        System.out.println(repository.query(new StudentSpecificationById(3)));

    }
}
