package chirp.service.representations;

import chirp.model.User;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class UserRepresentation {
	
	private String username;
	private String realname;
	
	@JsonCreator
	public UserRepresentation(@JsonProperty("username") String username,
							  @JsonProperty("realname")String realname) {
		this.username = username;
		this.realname = realname;
	}
	
	public UserRepresentation(User user) {
		this.username = user.getUsername();
		this.realname = user.getRealname();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRealname() {
		return realname;
	}
	
	public void setRealname(String realname) {
		this.realname = realname;
	}
}
