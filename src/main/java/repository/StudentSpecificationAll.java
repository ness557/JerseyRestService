package repository;

import model.Student;

import java.util.Objects;

public class StudentSpecificationAll implements StudentSpecification, StudentJdbcSpecification {

    private String sqlStr;

    public StudentSpecificationAll() {

        sqlStr = "";
    }

    @Override
    public String toSql() {
        return sqlStr;
    }

    @Override
    public boolean specified(Student student) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSpecificationAll that = (StudentSpecificationAll) o;
        return Objects.equals(sqlStr, that.sqlStr);
    }
}
