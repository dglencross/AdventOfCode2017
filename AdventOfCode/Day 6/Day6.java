import java.util.ArrayList;
import java.util.List;


public class Day6 {

	public static int CountIterations(int[] banks) {
		int count = 0;
		
		List<int[]> records = new ArrayList<int[]>();
		
		int biggestLocation = 0;
		
		while(!seenBefore(records, banks)) {
			records.add(banks.clone());
			biggestLocation = getBiggestLocation(banks);
			
			count += 1;
			redistribute(banks, biggestLocation);
		}
		
		return count;
	}

	public static int CountLoop(int[] banks) {
		int count = 0;
		
		List<int[]> records = new ArrayList<int[]>();
		
		int biggestLocation = 0;
		
		while(!seenBefore(records, banks)) {
			records.add(banks.clone());
			biggestLocation = getBiggestLocation(banks);
			
			count += 1;
			redistribute(banks, biggestLocation);
		}
		
		int[] finalArray = banks.clone();
		
		return count - getFirstInstance(records, finalArray);
	}
	
	public static int getFirstInstance(List<int[]> records, int[] record) {
		for(int i=0; i<records.size(); i++) {
			if (arraysMatch(records.get(i), record)) {
				return i;
			}
		}
		throw new RuntimeException("Didn't find the array");
	}
	
	public static boolean seenBefore(List<int[]> records, int[] record) {
		for(int[] r : records) {
			if (arraysMatch(r, record)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean arraysMatch(int[] a, int[] b) {
		if (a.length != b.length) return false;
		
		for(int i=0;i<a.length;i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	private static int getBiggestLocation(int[] banks) {
		int biggestLocation = 0;
		int biggestValue = 0;
		
		for(int i=0;i<banks.length;i++) {
			if (banks[i] > biggestValue) {
				biggestValue = banks[i];
				biggestLocation = i;
			}
		}
		
		return biggestLocation;
	}
	
	public static void redistribute(int[] banks, int location) {
		
		printLine(banks);
		
		if (location >= banks.length) {
			throw new RuntimeException("Pre-empting an ArrayIndexOutOfBoundsException, location is " +  location);
		}
		
		int total = banks[location];
		banks[location] = 0;
		
		int i=location + 1;
		if (i >= banks.length) i = 0;
		while (total > 0) {
			System.out.println("i is " + i + ", length is " + banks.length);
			banks[i] += 1;
			total -= 1;
			i += 1;
			if (i >= banks.length) i = 0;
			printLine(banks);
		}
	}
	
	public static void printLine(int[] banks) {
		for(int i=0; i<banks.length; i++) {
			System.out.print(banks[i] + " ");
		}
		System.out.println("");
	}
	
}
