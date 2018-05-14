package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;
import model.StudentStore;
import model.StudentStoreLocal;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/studentslocal")
public class StudentRestService {

    private StudentStore studentStore = new StudentStoreLocal();

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStudents(){

        String res = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            res = mapper.writeValueAsString(studentStore.getStudents());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    @GET
    @Path("/students/{studentid}")
    public String getStudent(@PathParam("studentid") int id){

        return studentStore.getStudent(id).toString();
    }

    @PUT
    @Path("/students/{studid}/{name}/{group}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addStudent(@PathParam("studid") int studId,
                             @PathParam("name") String name,
                             @PathParam("group") String group){
        return String.valueOf(
                studentStore.addStudent(new Student(studId, name, group))
        );
    }

    @DELETE
    @Path("/students/{studentid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeStudent(@PathParam("studentid") int studid){

        return String.valueOf(
                studentStore.deleteStudent(studid)
        );
    }

    @POST
    @Path("/students/{id}/{name}/{group}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudent(@PathParam("id") int id,
                                @PathParam("name") String name,
                                @PathParam("group") String group){

        return String.valueOf(
                studentStore.updateStudent(new Student(id, name, group)));
    }



}
