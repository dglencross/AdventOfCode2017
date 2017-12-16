
import org.junit.Assert;
import org.junit.Test;


public class Day15Tests {

	@Test
	public void test_dummy_1() {
		Assert.assertEquals(588, Day15.solution1(40000000, 65L, 8921L));
	}

	@Test
	public void test_solution_1() {
		Assert.assertEquals(600, Day15.solution1(40000000, 699L, 124L));
	}
	
	@Test
	public void test_dummy_2() {
		Assert.assertEquals(309, Day15.solution2(5000000, 65L, 8921L));
	}
	
	@Test
	public void test_solution_2() {
		Assert.assertEquals(313, Day15.solution2(5000000, 699L, 124L));
	}
}
