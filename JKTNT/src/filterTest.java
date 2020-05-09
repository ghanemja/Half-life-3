import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class filterTest {

	@Test
	void testFilter() {
		ArrayList<Game> list = new ArrayList<Game>();
		Game g = new Game("DeadLand", "deadl.csv");
		Game g1 = new Game("PacMan", "pacman.csv");
		list.add(g);
		list.add(g1);
		filter f = new filter(list);
		assertEquals(f.getGames(), "[DeadLand, PacMan]");
	}

	@Test
	void testRateHitoLo() {
		ArrayList<Game> list = new ArrayList<Game>();
		Game g = new Game("DeadLand", "deadl.csv");
		Game g1 = new Game("PacMan", "pacman.csv");
		list.add(g);
		list.add(g1);
		filter f = new filter(list);
		assertEquals(f.atoZ(), list);

	}
}