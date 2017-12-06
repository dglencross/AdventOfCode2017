import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class Day6Tests {

	@Test
	public void test_1() {
		int[] banks = {0,2,7,0};
		
		Assert.assertEquals(5, Day6.CountIterations(banks));
	}
	
	@Test
	public void test_2() {
		int[] banks = {0,2,7,0};
		
		Assert.assertEquals(4, Day6.CountLoop(banks));
	}
	
	@Test
	public void test_solution_1() {
		int[] banks = {5,1,10,0,1,7,13,14,3,12,8,10,7,12,0,6};
		
		Assert.assertEquals(5042, Day6.CountIterations(banks));
	}
	
	@Test
	public void test_solution_2() {
		int[] banks = {5,1,10,0,1,7,13,14,3,12,8,10,7,12,0,6};
		
		Assert.assertEquals(1086, Day6.CountLoop(banks));
	}
	
	@Test
	public void test_redistribute() {
		int[] banks = {0,2,7,0};
		int location = 2;
		
		Day6.redistribute(banks, location);
		
		Assert.assertEquals(2, banks[0]);
		Assert.assertEquals(4, banks[1]);
		Assert.assertEquals(1, banks[2]);
		Assert.assertEquals(2, banks[3]);
	}
	
	@Test
	public void test_redistribute_2() {
		int[] banks = {0,2,3,4};
		int location = 3;
		
		Day6.redistribute(banks, location);
	}
	
	@Test
	public void test_if_can_check_list_for_arrays() {
		List<int[]> records = new ArrayList<int[]>();
		
		int[] check = {0,2,7,0};
		
		records.add(check.clone());
		
		Assert.assertTrue(records.contains(check));
	}

}
