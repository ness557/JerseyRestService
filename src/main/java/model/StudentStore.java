package model;

import java.util.Collection;

public interface StudentStore {

    Collection<Student> getStudents();
    Student getStudent(int id);
    int addStudent(Student student);
    int deleteStudent(int id);

}
