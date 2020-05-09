import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void testUser() {
		User u = new User("Stephan");
		assertEquals(u.getUserName(), "Stephan");
	}

	@Test
	void testIsUser() throws IOException {
		User u = new User("Stephan");
		assertTrue(u.isUser("Stephan"));
		assertFalse(u.isAdmin("Stephan"));		
	}

	@Test
	void testIsAdmin() throws IOException {
		User u = new User("Stephan");
		Admin p = new Admin("Penelope");
		assertFalse(u.isAdmin("Stephan"));	
	}

	@Test
	void testIsMod() throws IOException {
		Moderator m = new Moderator("m");
		assertTrue(m.isMod("m"));
	}

	@Test
	void testSearchFile() {
		User u = new User("user");
		assertTrue(u.searchFile("user", "pass"));
	}

	@Test
	void testLoginUser() {
		User u = new User("Stephan");
		assertTrue(u.loginUser("Stephan", "password"));
	}

	@Test
	void testLogout() {
		User u = new User("Stephan");
		assertTrue(u.logout());
	}

//	@Test
//	void testPostComment() {
//		User u = new User("Stephan");
//		Game g = new Game("PacMan", "/images/PacMan.png");
//		assertTrue(u.postComment("This is awesome", g, 2));
//	}

	
	@Test
	void testRegisterUser() {
		User u = new User("Stephan");
	//	assertTrue(u.registerUser("Stephan", "password"));
	}

	@Test
	void testGetUserName() {
		User u = new User("Stephan");
		assertEquals(u.getUserName(), "Stephan");
	}

	@Test
	void testGetRole() {
		User u = new User("Stephan");
		assertEquals(u.getRole(), 0);
		Admin a = new Admin("user2");
//		assertEquals(a.getRole(), 2);
		Moderator m = new Moderator("m");
//		assertEquals(m.getRole(), 1);
	}

}
