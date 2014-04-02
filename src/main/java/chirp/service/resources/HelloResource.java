package chirp.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/hello")
public class HelloResource {

	/**
	@GET
	@Produces("text/html")
	public String getHelloWithName(@QueryParam("name") String name) {
		if (name == null) {
			return "Hello!";
		} else {
			return "Hello " + name + "!";
		}
	}
	*/

	@GET
	public String getHelloWithName() {
		return "Hello!";
	}
	
	@GET
	@Path("{name}")
	public String getHelloWithName(@PathParam("name") String name) {
		if (name == null) {
			return "Hello!";
		}
		return "Hello " + name + "!";
	}
	
}
