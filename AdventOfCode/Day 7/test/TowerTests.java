import static org.junit.Assert.*;

import org.junit.Test;


public class TowerTests {

	private Tower tower;
	
	@Test
	public void test_instantiate() {
		tower = new Tower("fwft (72) -> ktlj, cntj, xhth");
		
		assertEquals("fwft", tower.getName());
		assertEquals(72, tower.getWeight());
		
		assertTrue(tower.getChildren().contains("ktlj"));
		assertTrue(tower.getChildren().contains("cntj"));
		assertTrue(tower.getChildren().contains("xhth"));
	}

}
