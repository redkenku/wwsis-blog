package pl.wwsis.zpp.blog.model.alternatives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.wwsis.zpp.blog.model.Comment;
import pl.wwsis.zpp.blog.model.Post;

public class Post5 extends Post implements PostApi{

	private List<String> tags = new ArrayList<String>();
	
	public Post5(String title, String text) {
		super(title, text);
	}

	public void addTag(String tag) {
		if (!tags.contains(tag) && tags.size() != 5) {
			tags.add(tag);
		}
	}

	public Set<String> getTags() {
		return Collections.unmodifiableSet(new HashSet<String>(tags));
	}

	public List<Comment> getLastComments() {
		Collections.sort(comments, new Comparator<Comment>() {
			public int compare(Comment o1, Comment o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});
		return getComments();
	}

}
