import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day7 {
	
	private static Map<String, Tower> getLookups(List<String> entries) {
		Map<String, Tower> lookup = new HashMap<String, Tower>();
		
		for(String entry : entries) {
			Tower t = new Tower(entry);
			lookup.put(t.getName(), t);
		}
		
		for(Tower t : lookup.values()) {
			for(String child : t.getChildren()) {
				lookup.get(child).setParent(t);
			}
		}
		
		return lookup;
	}
	
	public static Tower getBottom(List<String> entries) {
		Map<String, Tower> lookup = getLookups(entries);
		
		// pick a random tower and traverse up until parent is null
		Tower randomTower = lookup.entrySet().iterator().next().getValue();
		Tower parent = null;
		
		if (randomTower.getParent() == null) {
			return randomTower;
		} else {
			parent = randomTower.getParent();
		}
		
		while (parent != null) {
			randomTower = randomTower.getParent();
			parent = randomTower.getParent();
		}
		
		return randomTower;
	}

	public static String BuildTree(List<String> entries) {
		return getBottom(entries).getName();
	}
	
	public static void PrintDiscSizes(List<String> entries) {
		Tower bottom = getBottom(entries);
		Map<String,Tower> lookup = getLookups(entries);
		
		for(String child : bottom.getChildren()) {
			System.out.println(lookup.get(child).getName() + " : " + lookup.get(child).getWeight());
			System.out.println(getWeightOfChildren(child, lookup));
		}
	}
	
	public static void PrintDiscSizesForItem(List<String> entries, String name) {
//		Tower bottom = getBottom(entries);
		Map<String,Tower> lookup = getLookups(entries);
		
		for(String child : lookup.get(name).getChildren()) {
			System.out.println(lookup.get(child).getName() + " : " + lookup.get(child).getWeight());
			System.out.println(getWeightOfChildren(child, lookup));
		}
	}
	
	private static int getWeightOfChildren(String t, Map<String,Tower> lookup) {
		
		int total = lookup.get(t).getWeight();
		
		if (lookup.get(t).getChildren().isEmpty()) {
			return total;
		}
		
		for(String c : lookup.get(t).getChildren()) {
			total += getWeightOfChildren(c, lookup);
		}
		
		return total;
		
	}
	
	private static int getWeight(String t, Map<String,Tower> lookup) {
		
		int total = lookup.get(t).getWeight();
		
		int myWeight = lookup.get(t).getWeight();
		
		List<Integer> childWeights = new ArrayList<Integer>();
		for(String child : lookup.get(t).getChildren()) {
			total += lookup.get(child).getWeight();
		}
		
		return total;
	}
	
}
