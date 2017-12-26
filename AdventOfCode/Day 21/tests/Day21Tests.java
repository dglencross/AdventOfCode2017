import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class Day21Tests {

	private List<String> input;
	private List<String> input_2;
	private List<String> rules;
	
	@Before
	public void before() {
		input = new ArrayList<>();
		
		input.add(".#.");
		input.add("..#");
		input.add("###");
		
		input_2 = new ArrayList<String>();
		
		input_2.add("#..#");
		input_2.add("....");
		input_2.add("....");
		input_2.add("#..#");
		
		rules = new ArrayList<String>();
		
		rules.add("../.# => ##./#../...");
		rules.add(".#./..#/### => #..#/..../..../#..#");
	}
	
	@Test
	public void test_rule_match_normal() {
	
		List<String> in = new ArrayList<>();
		in.add("..");
		in.add(".#");
		
		String result = Day21.getMatchingRule(in, Day21.translateRules(rules));
		
		Assert.assertEquals("##./#../...", result);
	}
	
	@Test
	public void test_rule_match_reverse() {
	
		List<String> in = new ArrayList<>();
		in.add(".#");
		in.add("..");
		
		String result = Day21.getMatchingRule(in, Day21.translateRules(rules));
		
		Assert.assertEquals("##./#../...", result);
	}
	
	@Test
	public void test_rule_match_flip() {
	
		List<String> in = new ArrayList<>();
		in.add("..");
		in.add("#.");
		
		String result = Day21.getMatchingRule(in, Day21.translateRules(rules));
		
		Assert.assertEquals("##./#../...", result);
	}
	
	@Test
	public void test_rule_match_flip_reverse() {
	
		List<String> in = new ArrayList<>();
		in.add("#.");
		in.add("..");
		
		String result = Day21.getMatchingRule(in, Day21.translateRules(rules));
		
		Assert.assertEquals("##./#../...", result);
	}
	
	@Test
	public void test_dummy_input() {
	
		List<String> result = Day21.enhanceOnce(input, Day21.translateRules(rules));
		
		Assert.assertEquals(4, result.size());
		Assert.assertEquals("#..#",result.get(0));
		Assert.assertEquals("....",result.get(1));
		Assert.assertEquals("....",result.get(2));
		Assert.assertEquals("#..#",result.get(3));
	}
	
	@Test
	public void test_dummy_input_2() {
		List<String> result = Day21.enhance(input, Day21.translateRules(rules), 1);
		
		Assert.assertEquals(4, result.size());
		Assert.assertEquals("#..#",result.get(0));
		Assert.assertEquals("....",result.get(1));
		Assert.assertEquals("....",result.get(2));
		Assert.assertEquals("#..#",result.get(3));
	}
	
	@Test
	public void test_dummy_input_3() {
	
		List<String> result = Day21.enhance(input_2, Day21.translateRules(rules), 1);
		
		Assert.assertEquals(6, result.size());
		Assert.assertEquals("##.##.",result.get(0));
		Assert.assertEquals("#..#..",result.get(1));
		Assert.assertEquals("......",result.get(2));
		Assert.assertEquals("##.##.",result.get(3));
		Assert.assertEquals("#..#..",result.get(4));
		Assert.assertEquals("......",result.get(5));
	}

	@Test
	public void test_piece_back_together() {
		
		List<ArrayList<String>> input = new ArrayList<>();
		
		ArrayList<String> i0 = new ArrayList<String>();
		i0.add("##.");
		i0.add("#..");
		i0.add("...");
		
		input.add(i0);
		input.add(i0);
		input.add(i0);
		input.add(i0);		
		
		List<String> result = Day21.pieceBackTogether(input);
		
		Assert.assertEquals(6, result.size());
		Assert.assertEquals("##.##.", result.get(0));
		Assert.assertEquals("#..#..", result.get(1));
		Assert.assertEquals("......", result.get(2));
		Assert.assertEquals("##.##.", result.get(3));
		Assert.assertEquals("#..#..", result.get(4));
		Assert.assertEquals("......", result.get(5));
	}
	
	@Test
	public void test_dummy_2_iterations() {
		List<String> result = Day21.enhance(this.input, Day21.translateRules(this.rules), 2);
		
		
		Assert.assertEquals(12, Day21.CountOnSwitches(result));
	}
	
	@Test
	public void test_solution_1() {
		List<String> rulesInput = readInput("Day21.txt");
		
		List<String> result = Day21.enhance(this.input, Day21.translateRules(rulesInput), 12);
		
		System.out.println(result);
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
