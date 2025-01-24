package pl.wwsis.zpp.blog.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Post {

	private Date date;
	private String title;
	private String text;
	
	protected List<Comment> comments = new ArrayList<Comment>();
	
	public Post(String title, String text) {
		this.date = new Date();
		this.title = title;
		this.text = text;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
	public Date getDate() {
		return date;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}
	
	public List<Comment> getComments() {
		return Collections.unmodifiableList(comments);
	}
}
