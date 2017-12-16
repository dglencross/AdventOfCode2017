import java.util.ArrayList;
import java.util.List;


public class Day16 {

	public static List<String> dance(String input, String[] programs) {
		
		List<String> list = getList(programs);
		String[] instructions = input.split(",");
		
		for(String instruction : instructions) {
			if (instruction.charAt(0) == 'x') {
				instruction = instruction.replace("x", "");
				String[] parts = instruction.split("/");
				list = exchange(list, Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
			} else if (instruction.charAt(0) == 's') {
				instruction = instruction.replace("s", "");
				list = spin(list, Integer.valueOf(instruction));
			} else {
				instruction = instruction.substring(1, instruction.length());
				String[] parts = instruction.split("/");
				list = partner(list, parts[0], parts[1]);
			}
		}
		
		return list;
	}
	
	public static List<String> spin(List<String> p, int s) {
		
		int originalSize = p.size();
		List<String> sublist = p.subList(originalSize - s, originalSize);
		
		p.addAll(0, sublist);
		
		return p.subList(0, originalSize);
	}
	
	public static List<String> exchange(List<String> p, int a, int b) {
		
		String temp = p.get(b);
		
		p.set(b, p.get(a));
		p.set(a, temp);
		
		return p;
	}
	
	public static List<String> partner(List<String> p, String a, String b) {
		return exchange(p, p.indexOf(a), p.indexOf(b));
	}
	
	public static List<String> getList(String[] array) {
		List<String> newList = new ArrayList<String>();
		
		for(String s : array) {
			newList.add(s);
		}
		
		return newList;
	}
	
	public static String toString(List<String> list) {
		StringBuilder builder = new StringBuilder();
		
		for(String l : list) {
			builder.append(l);
		}
		
		return builder.toString();
	}
	
}
