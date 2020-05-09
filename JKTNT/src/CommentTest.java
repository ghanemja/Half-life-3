import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommentTest {

	@Test
	void testComment() {
		Comment c = new Comment("p", "hello", 3, 2);
		assertEquals(c.getUser(), "p");
		assertEquals(c.getRating(), 3);
	}

	@Test
	void testGetUser() {
		Comment c = new Comment("p", "hello", 3, 2);
		assertEquals(c.getUser(), "p");
	}

	@Test
	void testGetComment() {
		Comment c = new Comment("p", "hello", 3, 2);
		assertEquals(c.getComment(), "hello");
	}

	@Test
	void testGetRating() {
		Comment c = new Comment("p", "hello", 3, 2);
		assertEquals(c.getRating(), 3);
	}

	@Test
	void testGetCommentNum() {
		Comment c = new Comment("p", "hello", 3, 2);
		assertEquals(c.getCommentNum(), 2);
	}

}
