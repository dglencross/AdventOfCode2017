import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class Day20Tests {

	@Test
	public void test_parse_line() {
		Particle p = Day20.parseParticle("p=<1500,413,-535>, v=<-119,22,36>, a=<-5,-12,3>");
		
		Assert.assertEquals("1500413-535-1192236-5-123", p.getState());
	}
	
	@Test
	public void run_dummy_part1() {
		List<String> lines = readInput("Day20Dummy.txt");
		
		Assert.assertEquals(0, Day20.run(lines, 4));
	}
	
	@Test
	public void run_solution_part1() {
		List<String> lines = readInput("Day20.txt");
		
		Assert.assertEquals(161, Day20.run(lines, 10000));
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
