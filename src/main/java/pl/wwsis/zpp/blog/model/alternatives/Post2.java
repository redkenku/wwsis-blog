package pl.wwsis.zpp.blog.model.alternatives;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.wwsis.zpp.blog.model.Comment;
import pl.wwsis.zpp.blog.model.Post;

public class Post2 extends Post implements PostApi {

	private Map<String, Boolean> tags = new HashMap<String, Boolean>();
	
	public Post2(String title, String text) {
		super(title, text);
	}

	public void addTag(String tag) {
		if (tags.size() < 5) {
			tags.put(tag, true);
		}
	}

	public Set<String> getTags() {
		return tags.keySet();
	}

	public List<Comment> getLastComments() {
		Collections.sort(comments, new Comparator<Comment>() {
			public int compare(Comment o1, Comment o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});
		if (comments.size() < 4) {
			return getComments();
		} else {
			return Arrays.asList(comments.get(0), comments.get(1), comments.get(2));
		}
	}

}
