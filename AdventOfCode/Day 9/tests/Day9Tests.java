
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class Day9Tests {

	@Test
	public void test_solution_1() {
		Assert.assertEquals(11347, Day9.score(readInput("Day9Input.txt").get(0)));
	}
	
	@Test
	public void test_solution_2() {
		Assert.assertEquals(5404, Day9.countGarbage(readInput("Day9Input.txt").get(0)));
	}
	
	@Test
	public void score_1() {
		Assert.assertEquals(1, Day9.score("{}"));
	}
	
	@Test
	public void score_6() {
		Assert.assertEquals(6, Day9.score("{{{}}}"));
	}
	
	@Test
	public void score_5() {
		Assert.assertEquals(5, Day9.score("{{},{}}"));
	}

	@Test
	public void score_16() {
		Assert.assertEquals(16, Day9.score("{{{},{},{{}}}}"));
	}
	
	@Test
	public void score_garbage_1() {
		Assert.assertEquals(1, Day9.score("{<a>,<a>,<a>,<a>}"));
	}
	
	@Test
	public void score_garbage_9() {
		Assert.assertEquals(9, Day9.score("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
	}
	
	@Test
	public void score_garbage_9_2() {
		Assert.assertEquals(9, Day9.score("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
	}
	
	@Test
	public void score_garbage_3() {
		Assert.assertEquals(3, Day9.score("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
	}
	
	@Test
	public void score_garbage_1_2() {
		Assert.assertEquals(1, Day9.score("{<{},{},{{}}>}"));
	}
	
	@Test
	public void score_garbage_3_2() {
		Assert.assertEquals(3, Day9.score("{{<!>},{<!>},{<!>},{<a>}}"));
	}
	
	@Test
	public void count_garbage_0() {
		Assert.assertEquals(0, Day9.countGarbage("<>"));
	}
	
	@Test
	public void count_garbage_3() {
		Assert.assertEquals(3, Day9.countGarbage("<<<<>"));
	}
	
	@Test
	public void count_garbage_17() {
		Assert.assertEquals(17, Day9.countGarbage("<random characters>"));
	}
	
	@Test
	public void count_garbage_2() {
		Assert.assertEquals(2, Day9.countGarbage("<{!>}>"));
	}
	
	@Test
	public void count_garbage_0_2() {
		Assert.assertEquals(0, Day9.countGarbage("<!!>"));
	}
	
	@Test
	public void count_garbage_0_3() {
		Assert.assertEquals(0, Day9.countGarbage("<!!!>>"));
	}
	
	@Test
	public void count_garbage_10() {
		Assert.assertEquals(10, Day9.countGarbage("<{o\"i!a,<{i<a>"));
	}
	
	private List<String> readInput(String fileName) {
		List<String> lines = new ArrayList<String>();
		try {
			File file = new File("/Users/dave/workspace/AdventOfCode/tests/" + fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			fileReader.close();
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
