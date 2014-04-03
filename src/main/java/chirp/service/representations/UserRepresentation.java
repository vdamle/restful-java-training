package chirp.service.representations;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import chirp.model.User;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class UserRepresentation {
	
	private String username;
	private String realname;
	private URI self;
	
	public UserRepresentation() { /* required for xml representation */ }
	
	@JsonCreator
	public UserRepresentation(@JsonProperty("self") URI self,
							  @JsonProperty("username") String username,
							  @JsonProperty("realname")String realname) {
		this.self = self;
		this.username = username;
		this.realname = realname;
	}
	
	public UserRepresentation(User user) {
		this.self = UriBuilder.fromPath("user").path(user.getUsername()).build();
		this.username = user.getUsername();
		this.realname = user.getRealname();
	}
	
	@XmlElement
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@XmlElement
	public String getRealname() {
		return realname;
	}
	
	public void setRealname(String realname) {
		this.realname = realname;
	}

	@XmlElement
	public URI getSelf() {
		return self;
	}

	public void setSelf(URI self) {
		this.self = self;
	}
}
