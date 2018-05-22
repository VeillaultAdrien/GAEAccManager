package accManager;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

@Path("/serv")
public class Bank_Client_Management {
	
		static {
			ObjectifyService.register(Bank_Client.class);
		}

	@GET
    @Path("ajout")
    @Produces("text/html")
     public String createClient(){
		ofy().save().entity(new Bank_Client("Truc", "John", 20000, false)).now();
		return ("<html><body><h1>Client ajouté !</h1></body></html>");
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
	
	//liste
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
	public Bank_Client[] listClient() {
//		Iterable<Key<Bank_Client>> allKeys = ofy().load().type(Bank_Client.class).keys();
//		List<Bank_Client> c = ofy().load().type(Bank_Client.class).keys(allKeys);
		
		Iterable<Bank_Client> c = ofy().load().type(Bank_Client.class);

		System.out.println(c);
		Iterator<Bank_Client> iterator = c.iterator();
		Bank_Client[] listClient = new Bank_Client[100];
		while (iterator.hasNext()){
			int i = 0;
			Bank_Client next = iterator.next();
			listClient[i] = next;
			i++;
		}
		return listClient;
	}
	

}
