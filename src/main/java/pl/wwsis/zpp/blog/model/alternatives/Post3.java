package pl.wwsis.zpp.blog.model.alternatives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.wwsis.zpp.blog.model.Comment;
import pl.wwsis.zpp.blog.model.Post;

public class Post3 extends Post implements PostApi {

	private Collection<String> tags = new ArrayList<String>();
	
	public Post3(String title, String text) {
		super(title, text);
	}

	public void addTag(String tag) {
		if (tags.size() >=5) {
			return;
		} else {
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
		if (comments.size() <= 3) {
			return getComments();
		} else {
			return Arrays.asList(comments.get(0), comments.get(1), comments.get(2));
		}
	}

}
