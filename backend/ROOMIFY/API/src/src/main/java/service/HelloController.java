package service;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class HelloController {
	@Path("/h")
	@GET
	public String h() {
		return "helol  bojm3a";
	}

}
