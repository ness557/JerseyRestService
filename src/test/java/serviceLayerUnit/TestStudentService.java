package serviceLayerUnit;

import model.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.StudentHibernateRepository;
import repository.StudentSpecification;
import repository.StudentSpecificationAll;
import repository.StudentSpecificationById;
import service.StudentServiceImpl;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestStudentService {

    @Mock
    private StudentHibernateRepository repository;

    private StudentServiceImpl service;
    private Student student;


    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);
        service = new StudentServiceImpl(repository);
        student = new Student(1, "Roman", "KI-144");
    }


    @Test
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
