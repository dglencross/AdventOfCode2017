import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SpiralMemoryTests {
	
	private SpiralMemory memory;
	
	@Before
	public void Before() {
		memory = new SpiralMemory();
	}

	@Test
	public void test_1() {
		Assert.assertEquals(1, memory.CalculateSizeOfSquare(1));
	}
	
	@Test
	public void test_6() {
		Assert.assertEquals(3, memory.CalculateSizeOfSquare(6));
	}
	
	@Test
	public void test_10() {
		Assert.assertEquals(5, memory.CalculateSizeOfSquare(10));
	}
	
	@Test
	public void test_23() {
		Assert.assertEquals(5, memory.CalculateSizeOfSquare(23));
	}
	
	@Test
	public void test_26() {
		Assert.assertEquals(7, memory.CalculateSizeOfSquare(26));
	}
	
	@Test
	public void printGrid5() {
		long[][] grid = memory.allocatedMemory(5);
		memory.printGrid(grid);
		Assert.assertEquals(17, grid[0][0]);
		Assert.assertEquals(5, grid[1][1]);
		Assert.assertEquals(1, grid[2][2]);
		Assert.assertEquals(25, grid[4][4]);
		Assert.assertEquals(22, grid[4][1]);
	}
	
	@Test
	public void printGrid7() {
		long[][] grid = memory.allocatedMemory(7);
		memory.printGrid(grid);
	}
	
	@Test
	public void test_find_1() {
		long[][] grid = memory.allocatedMemory(5);
		
		Pair location = memory.getNumberLocation(1, grid);
		Assert.assertEquals(2, location.getColumn());
		Assert.assertEquals(2, location.getRow());
	}

}
