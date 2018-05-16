package repository;

import model.Student;

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
    public boolean equals(Object obj) {

        return  (obj instanceof StudentSpecificationById && ((StudentSpecificationById) obj).id == this.id);

    }
}
