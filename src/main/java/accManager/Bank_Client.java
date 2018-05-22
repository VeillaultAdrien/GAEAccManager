package accManager;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import exception.QueryException;

@Entity
public class Bank_Client{
	@Id public String lastName;
	@Index public String firstName;
	public float account;
	public Boolean risk;
	
	public Bank_Client(String lastName, String firstName, float account, Boolean risk) throws QueryException {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.account = account;
		this.risk = risk;
	}
	
	public Bank_Client() {
		
	}
	
	public Boolean getRisk() {
		return(this.risk);
	}
	
	public void setRisk(Boolean risk) {
		this.risk = risk;
	}
	
	@Override
    public String toString() {
		String json = String.format("Bank_Client {lastName=", lastName, ", firstName=", firstName,", account=", account, ", risk=", risk, "}" );
        return json;
    }
}
