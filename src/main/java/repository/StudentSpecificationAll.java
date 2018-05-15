package repository;

import model.Student;

public class StudentSpecificationAll implements StudentSpecification, StudentJdbcSpecification {

    @Override
    public String toSql() {
        return "SELECT * FROM students";
    }

    @Override
    public boolean specified(Student student) {
        return true;
    }
}
