package serviceLayerUnit;

import model.Student;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import repository.StudentJdbcRepository;
import repository.StudentSpecification;
import repository.StudentSpecificationAll;
import repository.StudentSpecificationById;
import service.StudentServiceImpl;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class TestStudentService {

    @Mock
    private StudentJdbcRepository repository;

    private StudentServiceImpl service;
    private Student student;


    @BeforeClass
    public void setup(){

        student = new Student(1, "Roman", "KI-144");
    }

    @BeforeMethod
    public void setupMethod(){

        MockitoAnnotations.initMocks(this);
        service = new StudentServiceImpl(repository);
    }

    @Test()
    public void addStudent_should_create_student(){

        when(repository.addStudent(student)).thenReturn(1);
        int result = service.addStudent(student);

        assertEquals(result, 1);
        verify(repository).addStudent(student);
    }

    @Test
    public void addStudent_should_not_create_student(){

        when(repository.addStudent(student)).thenReturn(0);
        int result = service.addStudent(student);

        assertEquals(result, 0);
        verify(repository).addStudent(student);
    }

    @Test
    public void updateStudent_should_update_student(){

        when(repository.updateStudent(student)).thenReturn(1);
        int result = service.updateStudent(student);

        assertEquals(result, 1);
        verify(repository).updateStudent(student);
    }

    @Test
    public void updateStudent_should_not_update_student(){

        when(repository.updateStudent(student)).thenReturn(0);
        int result = service.updateStudent(student);

        assertEquals(result, 0);
        verify(repository).updateStudent(student);
    }

    @Test
    public void removeStudent_should_remove_student(){

        when(repository.removeStudent(student)).thenReturn(1);
        int result = service.removeStudent(student);

        assertEquals(result, 1);
        verify(repository).removeStudent(student);
    }

    @Test
    public void removeStudent_should_not_remove_student(){

        when(repository.removeStudent(student)).thenReturn(0);
        int result = service.removeStudent(student);

        assertEquals(result, 0);
        verify(repository).removeStudent(student);
    }

    @Test
    public void getStudent_should_return_student(){

        int id = 1;

        List<Student> studentInList = new LinkedList<>();
        studentInList.add(student);

        StudentSpecification specification = new StudentSpecificationById(id);

        when(repository.query(specification))
                .thenReturn(studentInList);

        Student result = service.getStudentById(id);
        assertEquals(student, result);
        verify(repository).query(specification);
    }

    @Test
    public void getStudent_should_return_null(){

        int id = 1;
        List<Student> studentInList = new LinkedList<>();

        StudentSpecification specification = new StudentSpecificationById(id);

        when(repository.query(specification))
                .thenReturn(studentInList);

        Student result = service.getStudentById(id);
        assertNull(result);
        verify(repository).query(specification);
    }

    @Test
    public void getStudents_should_return_students(){

        StudentSpecification specification = new StudentSpecificationAll();
        List<Student> studentInList = new LinkedList<>();
        studentInList.add(student);

        when(repository.query(specification)).thenReturn(studentInList);

        List<Student> resultList = service.getStudents();

        assertEquals(studentInList, resultList);
        verify(repository).query(specification);
    }

    @Test
    public void getStudents_should_return_empty_list(){

        StudentSpecification specification = new StudentSpecificationAll();
        List<Student> studentInList = new LinkedList<>();

        when(repository.query(specification)).thenReturn(studentInList);

        List<Student> resultList = service.getStudents();

        assertTrue(resultList.isEmpty());
        assertEquals(studentInList, resultList);
        verify(repository).query(specification);
    }


}
