import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class Day7Tests {

	@Test
	public void test_dummy_input() {
		List<String> lines = readInput("Day7TestInput.txt");
		
		String bottom = Day7.BuildTree(lines);
		
		Assert.assertEquals("tknk", bottom);
	}
	
	@Test
	public void test_solution_1() {
		List<String> lines = readInput("Day7Input1.txt");
		
		String bottom = Day7.BuildTree(lines);
		
		Assert.assertEquals("ahnofa", bottom);
	}
	
	@Test
	public void test_part_2_dummy_input() {
		List<String> lines = readInput("Day7TestInput.txt");
		
		Day7.PrintDiscSizes(lines);
	}
	
	@Test
	public void test_solution_2() {
		List<String> lines = readInput("Day7Input1.txt");
		
		Day7.PrintDiscSizes(lines);
	}
	
	@Test
	public void test_luralcy_children() {
		List<String> lines = readInput("Day7Input1.txt");
		
		Day7.PrintDiscSizesForItem(lines,"luralcy");
	}
	
	@Test
	public void test_bvrxeo_children() {
		List<String> lines = readInput("Day7Input1.txt");
		
		Day7.PrintDiscSizesForItem(lines,"bvrxeo");
	}
	
	@Test
	public void test_ltleg_children() {
		List<String> lines = readInput("Day7Input1.txt");
		
		Day7.PrintDiscSizesForItem(lines,"ltleg");
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
