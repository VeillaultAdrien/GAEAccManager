package accManager;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.regex.Pattern;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.objectify.ObjectifyService;

import exception.QueryException;

@Path("/serv")
public class Bank_Client_Management {
	
		static {
			ObjectifyService.register(Bank_Client.class);
		}

	@GET
    @Path("ajout/{lastName}/{firstName}/{account}")
    @Produces(MediaType.APPLICATION_JSON)
     public String createClient(@PathParam("lastName") String ln, @PathParam("firstName") String fn, @PathParam("account") int acc) throws QueryException{
		
		try{
			if(ln.matches("^[a-zA-Z]+$"))
			{ofy().save().entity(new Bank_Client(ln, fn, acc, false)).now();
			Bank_Client c = ofy().load().type(Bank_Client.class).id(ln).now();
			return c.toString();}
			else throw new QueryException(ln+" is not a string");
		}
			catch (QueryException q) {
				q.setHttpError(400);
				q.setMessage(ln + " is not a string");
				q.setType("Error in the last name");
				Response.status(Status.BAD_REQUEST).entity(q).build();
				return q.toString();}
			
//			return q.toString();
			
		
		
	}
	
	//suppression
	@DELETE
    @Path("supp/{name}")
    @Produces("text/html")
     public String deleteClient(@PathParam("name") String n){
		Bank_Client c = ofy().load().type(Bank_Client.class).id(n).now();
		ofy().delete().entity(c);;
		return ("<html><body><h1>Client supprimé !</h1></body></html>");
	}
	
	//list
	@GET
	@Path("list/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bank_Client listClient(@PathParam("name") String n) {
		Bank_Client c = ofy().load().type(Bank_Client.class).id(n).now();
		return c;
	}
	
	

}

