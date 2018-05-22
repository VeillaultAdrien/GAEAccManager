package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class QueryException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String type = "QueryException";
	public String message;
	public int httpError;
	
	
	public QueryException(String m) {
		super(m);

	}
	
	public String getType() {
		return type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getHttpError() {
		return httpError;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setMessage(String m) {
		this.message = m;
	}

	public void setHttpError(int h) {
		this.httpError = h;
	}
	
	@Override
	public String toString() {
		String json =String.format("QueryException {type=", type ,", message=", message,", httpError=", httpError, "}" );
		System.out.println(json);
		return json;
	}
}
