package repository;

import model.Student;

public class StudentSpecificationAll implements StudentSpecification, StudentJdbcSpecification {

    private String sqlString;

    public StudentSpecificationAll() {

        sqlString = "";
    }

    @Override
    public String toSql() {
        return sqlString;
    }

    @Override
    public boolean specified(Student student) {
        return true;
    }

    @Override
    public boolean equals(Object obj) {

         return (obj instanceof StudentSpecificationAll && ((StudentSpecificationAll) obj).sqlString.equals(this.sqlString));
    }
}
