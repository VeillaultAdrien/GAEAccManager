package accManager;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import exception.QueryException;

@Entity
public class Bank_Client{
	@Id public String lastName;
	@Index public String firstName;
	public String account;
	public Boolean risk;
	
	public Bank_Client(String lastName, String firstName, String account, Boolean risk) throws QueryException {
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
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Override
    public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Bank_Client");
		buffer.append(", lastName=");
		buffer.append(lastName);
		buffer.append(", firstName=");
		buffer.append(firstName);
		buffer.append(", account=");
		buffer.append(account);
		buffer.append(", risk=");
		buffer.append(risk);
		buffer.append("}");
        String json = buffer.toString();
        return json;
    }
}

