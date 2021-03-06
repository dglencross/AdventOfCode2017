
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class Day8Tests {

	@Test
	public void test_parse_dummy_instructions() {
		List<String> instructions = readInput("Day8TestInput.txt");
		
		int largestValue = Day8.returnLargestValueAfterInstructions(instructions);
		
		Assert.assertEquals(1, largestValue);
	}
	
	@Test
	public void test_solution_1() {
		List<String> instructions = readInput("Day8Input.txt");
		
		int largestValue = Day8.returnLargestValueAfterInstructions(instructions);
		
		Assert.assertEquals(4066, largestValue);
	}
	
	@Test
	public void test_solution_2() {
		List<String> instructions = readInput("Day8Input.txt");
		
		int largestValue = Day8.returnLargestValueDuringInstructions(instructions);
		
		Assert.assertEquals(4829, largestValue);
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
