package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;
import repository.*;
import service.StudentService;
import service.StudentServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URL;

@Path("/jdbc")
public class StudentJdbcRestService {

    private StudentService service;

    public StudentJdbcRestService(){

        String path = null;
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource("db.properties");
        if(resource != null)
            path = resource.getFile();

        service = new StudentServiceImpl(new StudentJdbcRepository(path));
    }

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStudents(){

        String res = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            res = mapper.writeValueAsString(
                    service.getStudents());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    @GET
    @Path("/students/{studentid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStudent(@PathParam("studentid") int id){

        String res = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            res = mapper.writeValueAsString(service.getStudentById(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    @PUT
    @Path("/students/{studid}/{name}/{group}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addStudent(@PathParam("studid") int studId,
                             @PathParam("name") String name,
                             @PathParam("group") String group){
        return String.valueOf(
                service.addStudent(new Student(studId, name, group)));
    }

    @DELETE
    @Path("/students/{studentid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeStudent(@PathParam("studentid") int studid){

        return String.valueOf(
                service.removeStudent(studid));
    }

    @POST
    @Path("/students/{id}/{name}/{group}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudent(@PathParam("id") int id,
                                @PathParam("name") String name,
                                @PathParam("group") String group){

        return String.valueOf(
                service.updateStudent(new Student(id, name, group)));
    }
}
