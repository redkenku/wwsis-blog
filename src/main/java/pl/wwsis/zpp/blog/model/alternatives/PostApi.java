package pl.wwsis.zpp.blog.model.alternatives;

import java.util.List;
import java.util.Set;

import pl.wwsis.zpp.blog.model.Comment;

public interface PostApi {

	void addTag(String tag);
	Set<String> getTags();
	
	List<Comment> getLastComments();
	
	List<Comment> getComments();
	void addComment(Comment c);
}
