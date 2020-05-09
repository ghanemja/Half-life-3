import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import javax.swing.JButton;


class GameTest {

	@Test
	void testGameStringString() {
		Game g = new Game("PacMan", "/images/PacMan.png");
		assertEquals(g.getName(), "PacMan");
	}

	@Test
	void testGameIntBooleanStringStringDoubleStringString() {
		Game g = new Game(0, true, "PacMan", "/images/PacMan.png", 20.10, "pacman.csv", "Pacman game eats stuff");
		assertEquals(g.getName(), "PacMan");
		assertEquals(g.getPrice(), 20.10, 0.0);
		assertEquals(g.getGameId(), 0);
		assertEquals(g.getDescription(), "Pacman game eats stuff");
		assertEquals(g.getCommentFile(), ".//comments//pacman.csv");
	}


}
