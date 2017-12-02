import java.util.ArrayList;

public class Day2 {
	public static int GetChecksum(ArrayList<ArrayList<Integer>> numbers) {
		
		int total = 0;
		
		for(ArrayList<Integer> row : numbers) {
			int biggest = Integer.MIN_VALUE;
			int smallest = Integer.MAX_VALUE;
			
			for(Integer value : row) {
				if (value < smallest) smallest = value;
				if (value > biggest) biggest = value;
			}
			
			total += (biggest - smallest);
		}
		
		return total;
	}
	
	private static int getOnlyDivisorResult(ArrayList<Integer> row) {
		
		for (int i=0; i < row.size(); i++) {
			for (int j=0;j<row.size(); j++) {
				if (i != j) {
					if (row.get(i) % row.get(j) == 0) {
						return row.get(i) / row.get(j);
					}
					if (row.get(j) % row.get(i) == 0) {
						return row.get(j) / row.get(i);
					}
				}
			}
		}
		
		return 0;
	}
	
	public static int GetDivisionChecksum(ArrayList<ArrayList<Integer>> numbers) {
		
		int total = 0;
		
		for(ArrayList<Integer> row : numbers) {
			total += getOnlyDivisorResult(row);
		}
		
		return total;
	}
}
