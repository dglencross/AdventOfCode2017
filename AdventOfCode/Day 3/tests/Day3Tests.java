import org.junit.Assert;
import org.junit.Test;


public class Day3Tests {

	@Test
	public void test_cost_1() {
		Day3 solution = new Day3();
		
		solution.allocateMemory(1);
		
		Assert.assertEquals(0, solution.calculateCost(1));
	}
	
	@Test
	public void test_cost_12() {
		Day3 solution = new Day3();
		
		solution.allocateMemory(12);
		
		Assert.assertEquals(3, solution.calculateCost(12));
	}
	
	@Test
	public void test_cost_23() {
		Day3 solution = new Day3();
		
		solution.allocateMemory(23);
		
		Assert.assertEquals(2, solution.calculateCost(23));
	}
	
	@Test
	public void test_cost_1024() {
		Day3 solution = new Day3();
		
		solution.allocateMemory(1024);
		
		Assert.assertEquals(31, solution.calculateCost(1024));
	}
	
	@Test
	public void test_cost_277678() {
		Day3 solution = new Day3();
		
		solution.allocateMemory(277678);
		
		Assert.assertEquals(475, solution.calculateCost(277678));
	}

}
