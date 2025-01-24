package pl.wwsis.zpp.blog.model.alternatives;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import pl.wwsis.zpp.blog.model.Comment;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RunWith(Parameterized.class)
public class PostSpecificationTest {

    private final PostApi post;

    // Constructor for parameterized test
    public PostSpecificationTest(PostApi post) {
        this.post = post;
    }

    // Parameterized input: instances of Post1 to Post8
    @Parameterized.Parameters
    public static Collection<Object[]> postImplementations() {
        return Arrays.asList(new Object[][]{
            {new Post1("test title", "test text")},
            {new Post2("test title", "test text")},
            {new Post3("test title", "test text")},
            {new Post4("test title", "test text")},
            {new Post5("test title", "test text")},
            {new Post6("test title", "test text")},
            {new Post7("test title", "test text")},
            {new Post8("test title", "test text")}
        });
    }

    @Test
    public void testAddTag_NoDuplicatesAndMaxLimit() {
        post.addTag("tag1");
        post.addTag("tag1"); // Duplicate
        post.addTag("tag2");
        post.addTag("tag3");
        post.addTag("tag4");
        post.addTag("tag5");
        post.addTag("tag6"); // Exceeds limit

        Set<String> tags = post.getTags();
        assertEquals(5, tags.size()); // Maximum limit is 5
        assertTrue(tags.contains("tag1"));
        assertFalse(tags.contains("tag6")); // Shouldn't be added
    }

    @Test
    public void testGetTags_EmptyByDefault() {
        Set<String> tags = post.getTags();
        assertNotNull(tags);
        assertTrue(tags.isEmpty());
    }

    @Test
    public void testGetTags_Immutable() {
        post.addTag("tag1");
        Set<String> tags = post.getTags();
        try {
            tags.add("newTag");
            fail("Tags set should be immutable!");
        } catch (UnsupportedOperationException e) {
            // Pass
        }
    }

    @Test
    public void testGetLastComments_ReturnsThreeMostRecent() {
        Comment c1 = new Comment("user1", "First comment");
        Comment c2 = new Comment("user2", "Second comment");
        Comment c3 = new Comment("user3", "Third comment");
        Comment c4 = new Comment("user4", "Fourth comment");

        post.addComment(c1);
        post.addComment(c2);
        post.addComment(c3);
        post.addComment(c4);

        List<Comment> lastComments = post.getLastComments();
        assertEquals(3, lastComments.size()); // Only 3 comments should be returned
    }

    @Test
    public void testGetLastComments_EmptyByDefault() {
        List<Comment> lastComments = post.getLastComments();
        assertNotNull(lastComments);
        assertTrue(lastComments.isEmpty());
    }

    @Test
    public void testGetLastComments_Immutable() {
        Comment c1 = new Comment("user1", "First comment");
        post.addComment(c1);

        List<Comment> lastComments = post.getLastComments();
        try {
            lastComments.add(new Comment("user2", "Second comment"));
            fail("Comments list should be immutable!");
        } catch (UnsupportedOperationException e) {
            // Pass
        }
    }

    @Test
    public void testAddComment_LinksToPost() {
        Comment c1 = new Comment("user1", "First comment");
        post.addComment(c1);

        List<Comment> comments = post.getLastComments();
        assertEquals(1, comments.size());
        assertTrue(comments.contains(c1));
    }
}
