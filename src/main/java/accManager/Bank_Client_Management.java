package accManager;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
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
    @Path("ajout/{lastName}/{firstName}/{account}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
     public String createClient(@PathParam("lastName") String ln, @PathParam("firstName") String fn, @PathParam("account") String acc, @PathParam("id") String id) throws QueryException{
		
		try{
			if(ln.matches("^[a-zA-Z]+$")){
				if(fn.matches("^[a-zA-Z]+$")) {
					if(acc.matches("^[0-9]+$")) {
						if(id.matches("^[0-9]+$")) {
							ofy().save().entity(new Bank_Client(ln, fn, acc, false, id)).now();
							Bank_Client c = ofy().load().type(Bank_Client.class).id(id).now();
							return c.toString();
						} else {
							String m = this.queryExceptionMessage(id);
							throw new QueryException(m, "id");
						}
					} else {
					String m = this.queryExceptionMessage(acc);
					throw new QueryException(m, "account");
				}				
			} else {
				String m = this.queryExceptionMessage(fn);
				throw new QueryException(m, "first name");
				}}
			else {
				String m = this.queryExceptionMessage(ln);
				throw new QueryException(m, "lastname");
			}
	}
		catch (QueryException q) {return q.toString();}		
	}
	
	public String queryExceptionMessage(String error) throws QueryException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(error);
		buffer.append(" bad value.");
		return buffer.toString();
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
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listAllClient() {
		List<Bank_Client> c = ofy().load().type(Bank_Client.class).list();
		String json = "";
		StringBuffer buffer = new StringBuffer();
		for (Bank_Client i : c) {
			buffer.append(i.toString());
		}
		json = buffer.toString();
		return json;
	}
	
	

}

