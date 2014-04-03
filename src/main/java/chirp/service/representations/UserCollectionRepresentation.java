package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import chirp.model.User;

@XmlRootElement
public class UserCollectionRepresentation {
	
	private Collection<UserRepresentation> userReps = new ArrayList<>();
	private URI self;
	
	public UserCollectionRepresentation() {
		
	}

	public UserCollectionRepresentation(Collection<User> users) {
		for (User user : users) {
			userReps.add(new UserRepresentation(user, true));
		}
		self = UriBuilder.fromPath("/user").build();
	}
	
	@XmlElement
	public Collection<UserRepresentation> getUser() {
		return Collections.unmodifiableCollection(userReps);
	}
	
	@XmlElement
	public URI getSelf() {
		return self;
	}
}
