package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;
import model.StudentStore;
import model.StudentStoreLocal;
import repository.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/jdbc")
public class StudentJdbcRestService {

    private StudentRepository repository = new StudentJdbcRepository();

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStudents(){

        String res = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            res = mapper.writeValueAsString(
                    repository.query(
                            new StudentSpecificationAll()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    @GET
    @Path("/students/{studentid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStudent(@PathParam("studentid") int id){

        StudentSpecification specification = new StudentSpecificationById(id);

        String res = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            res = mapper.writeValueAsString(
                    repository.query(specification));
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
                repository.addStudent(new Student(studId, name, group)));
    }

    @DELETE
    @Path("/students/{studentid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeStudent(@PathParam("studentid") int studid){

        return String.valueOf(
                repository.removeStudent(studid));
    }

    @POST
    @Path("/students/{id}/{name}/{group}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudent(@PathParam("id") int id,
                                @PathParam("name") String name,
                                @PathParam("group") String group){

        return String.valueOf(
                repository.updateStudent(new Student(id, name, group)));
    }



}
