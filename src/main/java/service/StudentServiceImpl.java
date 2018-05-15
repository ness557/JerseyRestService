package service;

import model.Student;
import repository.StudentRepository;
import repository.StudentSpecification;
import repository.StudentSpecificationAll;
import repository.StudentSpecificationById;

import java.util.List;

public class StudentServiceImpl implements StudentService{

    private StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository){

        this.repository = repository;
    }

    @Override
    public int addStudent(Student student) {
        return repository.addStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return repository.updateStudent(student);
    }

    @Override
    public int removeStudent(Student student) {
        return repository.removeStudent(student);
    }

    @Override
    public int removeStudent(int id) {
        return repository.removeStudent(id);
    }

    @Override
    public Student getStudentById(int id) {
        StudentSpecification specification = new StudentSpecificationById(id);
        List<Student> returnedList = repository.query(specification);
        if(!returnedList.isEmpty()){

            return returnedList.get(0);
        }
        return null;
    }

    @Override
    public List<Student> getStudents() {
        return repository.query(new StudentSpecificationAll());
    }
}
