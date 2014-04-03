package chirp.service.representations;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import chirp.model.Post;
import chirp.model.Timestamp;
import chirp.model.User;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class PostRepresentation {
	
	private String content;
	private String timestamp;
	
	public PostRepresentation() { /* For xml representation */ }
	
	@JsonCreator
	public PostRepresentation(@JsonProperty("content") String content,
							  @JsonProperty("timestamp") String timestamp) {
		this.content = content;
		this.timestamp = timestamp;
	}
	
	public PostRepresentation(Post post) {
		this.content = post.getContent();
		this.timestamp = post.getTimestamp().toString();
	}
	
	@XmlElement
	public String getContent() {
		return content;
	}
	
	public void setUsername(String content) {
		this.content = content;
	}
	
	@XmlElement
	public String getTimestamp() {
		return timestamp;
	}
}
