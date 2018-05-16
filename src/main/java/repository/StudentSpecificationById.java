package repository;

import model.Student;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class StudentSpecificationById implements StudentSpecification, StudentJdbcSpecification, StudentHibernateSpecification{

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
    public Criterion toCriteria() {
        return Restrictions.eq("id", id);
    }
}
