import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SpiralAdjacencyMemoryTests {

	private SpiralAdjacencyMemory memory;
	
	@Before
	public void Before() {
		memory = new SpiralAdjacencyMemory();
	}
	
	@Test
	public void test() {
		long[][] grid = memory.allocatedMemory(5);
		memory.printGrid(grid);
		Assert.assertEquals(147, grid[0][0]);
		Assert.assertEquals(5, grid[1][1]);
		Assert.assertEquals(1, grid[2][2]);
		Assert.assertEquals(25, grid[3][3]);
		Assert.assertEquals(747, grid[4][1]);
	}
	
	@Test
	public void test_answer() {
		int size = this.memory.CalculateSizeOfSquare(100);
		long[][] grid = memory.allocatedMemory(size,277678);
//		memory.printGrid(grid);
	}

}
