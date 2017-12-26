import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day21 {
	
	public static int CountOnSwitches(List<String> results) {
		int count = 0;
		
		for(String r : results) {
			r = r.replace(".", "");
			count += r.length();
		}
		
		return count;
	}
	
	public static Map<String, String> translateRules(List<String> rules) {
		Map<String,String> ruleMap = new HashMap<>();
		
		for(String rule : rules) {
			String[] parts = rule.split("=>");
			
			String match = parts[0].replace(" ", "");
			String output = parts[1].replace(" ", "");
			
			ruleMap.put(match, output);
			ruleMap.put(new StringBuffer(match).reverse().toString(), output);
			
			String[] matchParts = match.split("/");
			
			StringBuilder key = new StringBuilder();
			for(int i=matchParts.length -1; i >= 0; i--) {
				key.append(matchParts[i]);
				if (i > 0) {
					key.append("/");
				}
			}
			ruleMap.put(key.toString(), output);
			ruleMap.put(new StringBuffer(key.toString()).reverse().toString(), output);
		}
		
		return ruleMap;
	}
	
	public static List<String> enhance(List<String> grid, Map<String,String> rules, int enhancements) {
		
		for(int i=0; i < enhancements; i++) {
			grid = enhance(grid, rules);
		}
		
		return grid;
	}
	
	public static ArrayList<String> enhance(List<String> grid, Map<String,String> rules) {
		
		List<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		
		if (grid.size() % 3 == 0) {
			
			for(int i=0; i<grid.size(); i+=3) {
				for(int j=0; j<grid.size(); j+=3) {
					List<String> input = new ArrayList<>();
					
					input.add(grid.get(i).substring(j, j+3));
					input.add(grid.get(i+1).substring(j, j+3));
					input.add(grid.get(i+2).substring(j, j+3));
					
					results.add((ArrayList<String>) enhanceOnce(input, rules));
				}
			}
			
		} else if (grid.size() % 2 == 0) {
			
			for(int i=0; i<grid.size(); i+=2) {
				for(int j=0; j<grid.size(); j+=2) {
					List<String> input = new ArrayList<>();
					
					input.add(grid.get(i).substring(j, j+2));
					input.add(grid.get(i+1).substring(j, j+2));
					
					results.add((ArrayList<String>) enhanceOnce(input, rules));
				}
			}
			
		}

		return (ArrayList<String>) pieceBackTogether(results);
	}
	
	public static List<String> pieceBackTogether(List<ArrayList<String>> flatGrid) {
		
		List<String> result = new ArrayList<String>();
		
		if (flatGrid.size() == 1) {
			return flatGrid.get(0);
		}
		
//		for(ArrayList<String> l : flatGrid) {
//			result.addAll(l);
//		}
		
		double size = Math.sqrt(flatGrid.size());
		
//		for (int i=0; i < flatGrid.size(); i+=size) {
//			StringBuilder b = new StringBuilder();
//			for (int j=i; j < size + i; j++) {
//				b.append(flatGrid.get(j).get(0));
//			}
//			result.add(b.toString());
//		}
		
		result.add(flatGrid.get(0).get(0) + flatGrid.get(1).get(0));
		result.add(flatGrid.get(0).get(1) + flatGrid.get(1).get(1));
		result.add(flatGrid.get(0).get(2) + flatGrid.get(1).get(2));
		
		result.add(flatGrid.get(2).get(0) + flatGrid.get(3).get(0));
		result.add(flatGrid.get(2).get(1) + flatGrid.get(3).get(1));
		result.add(flatGrid.get(2).get(2) + flatGrid.get(3).get(2));
		
		return result;
	}
	
	public static String getMatchingRule(List<String> grid, Map<String,String> rules) {
		StringBuilder key = new StringBuilder();
		
		for (int i=0; i<grid.size(); i++) {
			key.append(grid.get(i));
			if (i < grid.size() - 1) {
				key.append("/");
			}
		}
		
		return rules.get(key.toString());
	}
	
	public static List<String> enhanceOnce(List<String> grid, Map<String,String> rules) {
		List<String> result = new ArrayList<String>();
		
		String[] output = getMatchingRule(grid, rules).split("/");
		for(String r : output) {
			result.add(r);
		}
		
		return result;
	}
	
}
