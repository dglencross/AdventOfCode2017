import java.util.ArrayList;
import java.util.List;

public class Day14 {
	
	public static Integer solution1(String input) {
		
		Integer result = 0;
		
		List<String> rows = getRowStrings(input);
		List<String> knots = knotRows(rows);
		
		for(String knot : knots) {
			String hex = knotToHexidecimal(knot);
			hex = hex.replace("0", "");
			result += hex.length();
		}
		
		return result;
	}
	
	public static Integer solution2(String input) {
		
		Integer result = 0;
		
		List<String> rows = getRowStrings(input);
		List<String> knots = knotRows(rows);
		List<String> hexes = new ArrayList<String>();
		
		for(String knot : knots) {
			String hex = knotToHexidecimal(knot);
			hexes.add(hex);
		}
		
		String[][] array = RowsToArray(hexes);
		
//		printArray(array);
		
		return GetNumberOfClusters(array);
	}
	
	public static void printArray(String[][] array) {
		for (int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println("");
		}
	}
	
	public static String[][] RowsToArray(List<String> rows) {
		String[][] array = new String[rows.size()][rows.get(0).length()];
		
		for (int i=0; i<rows.size(); i++) {
			for(int j=0; j<rows.get(i).length(); j++) {
				if (rows.get(i).charAt(j) == '1') {
					array[i][j] = "#";
				} else {
					array[i][j] = ".";
				}
			}
		}
		
		return array;
	}
	
	public static List<String> getRowStrings(String input) {
		List<String> rowStrings = new ArrayList<>();
		
		for(int i=0; i<128; i++) {
			rowStrings.add(input + "-" + i);
		}
		
		return rowStrings;
	}
	
	public static List<String> knotRows(List<String> rows) {
		List<String> knots = new ArrayList<String>();
		
		for(String row : rows) {
			int[] list256 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255};
			
			String knot = Day10.knot(list256, row);
			knots.add(knot);
		}
		
		return knots;
	}
	
	public static String knotToHexidecimal(String knot) {
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i<knot.length();i++) {
			Integer decimal = Integer.parseInt(Character.toString(knot.charAt(i)), 16);
			String string = Integer.toBinaryString(decimal);
			
			while(string.length() < 4) {
				string = "0" + string;
			}
			
			if (string.length() != 4) {
				throw new RuntimeException("String is too long! Length: " + string.length());
			}
			builder.append(string);
		}
		
		String string = builder.toString();
		if (string.length() != 128) {
			throw new RuntimeException("Hex is too long! Length: " + string.length());
		}
		
		return string;
	}
	
	public static Integer GetNumberOfClusters(String[][] array) {
		Integer maxNumber = 0;
		
		for (int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				
				if (getCharAt(i, j, array) == "#") {
					List<Pair> todoSet = new ArrayList<>();
					
					Day14.Pair p = new Day14.Pair(i, j);
					array[i][j] = "D";
					
					todoSet.addAll(GetReachableNodes(p, array));
					
					while(!todoSet.isEmpty()) {
						Pair p2 = todoSet.remove(0);
						todoSet.addAll(GetReachableNodes(p2, array));
						array[p2.i][p2.j] = "D";
					}
					
					maxNumber += 1;
				}
			}
		}
		
		printArray(array);
		
		return maxNumber;
		
	}
	
	public static List<Pair> GetReachableNodes(Pair p, String[][] array) {
		
		List<Pair> reachable = new ArrayList<Day14.Pair>();
		
		if (getCharAt(p.i-1, p.j, array) == "#") {
			reachable.add(new Pair(p.i-1,p.j));
		}
		if (getCharAt(p.i+1, p.j, array) == "#") {
			reachable.add(new Pair(p.i+1,p.j));
		}
		if (getCharAt(p.i, p.j-1, array) == "#") {
			reachable.add(new Pair(p.i,p.j-1));
		}
		if (getCharAt(p.i, p.j+1, array) == "#") {
			reachable.add(new Pair(p.i,p.j+1));
		}
		
		return reachable;
	}
	
	public static String getCharAt(int i, int j, String[][] array) {
		
		if (i < 0 || i >= array.length) {
			return ".";
		}
		
		if (j < 0 || j >= array.length) {
			return ".";
		}
		
		return array[i][j];
	}
	
	static class Pair {
		public int i;
		public int j;
		
		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
}
