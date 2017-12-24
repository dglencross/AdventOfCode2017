import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class Day18Tests {

	@Test
	public void test_dummy_instructions() {
		List<String> instructions = readInput("Day18Dummy.txt");
		
		Assert.assertEquals((Long)4L, Day18.part1(instructions));
	}
	
//	@Test
//	public void test_solution_1() {
//		List<String> instructions = readInput("Day18.txt");
//		
//		Assert.assertEquals((Long)8600L, Day18.part1(instructions));
//	}
	
	@Test
	public void test_part2_dummy() {
		List<String> instructions = readInput("Day18Dummy2.txt");
		
		Assert.assertEquals(3, Day18.part2(instructions));
	}
	
	@Test
	public void test_part2_solution() {
		List<String> instructions = readInput("Day18.txt");
		
		Assert.assertEquals(1, Day18.part2(instructions));
	}
	
//	@Test
//	public void test_cheating() {
//		List<String> instructions = readInput("Day18.txt");
//		
//		Day18_Cheat cheat = new Day18_Cheat(instructions);
//		Assert.assertEquals(1, cheat.part2());
//	}
	
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
