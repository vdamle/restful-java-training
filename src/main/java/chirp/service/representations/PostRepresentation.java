package chirp.service.representations;

import chirp.model.Post;
import chirp.model.Timestamp;
import chirp.model.User;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class PostRepresentation {
	
	private String content;
	private String timestamp;
	
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
	
	public String getContent() {
		return content;
	}
	
	public void setUsername(String content) {
		this.content = content;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

}
