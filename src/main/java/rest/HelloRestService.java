package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class HelloRestService{
    @GET
    @Path("/{param}")
    public String getMsg(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;

        return output;

    }
}