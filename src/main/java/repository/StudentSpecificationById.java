package repository;

import model.Student;

import java.util.Objects;

public class StudentSpecificationById implements StudentSpecification, StudentJdbcSpecification{

    private int id;

    public StudentSpecificationById(int id){

       this.id = id;
    }

    @Override
    public String toSql() {
        return "WHERE id = " + id;
    }

    @Override
    public boolean specified(Student student) {
        return student.getId() == id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSpecificationById that = (StudentSpecificationById) o;
        return id == that.id;
    }
}
