import java.util.ArrayList;
import java.util.List;


public class Day10 {

	public static int firstTwoValuesMultiplied(int[] list, int[] lengths) {
		list = updateList(list, lengths);
		
		return list[0] * list[1];
	}
	
	public static int[] updateList(int[] list, int[] lengths) {
		int position = 0;
		int skipSize = 0;
		
		for(int i=0;i<lengths.length; i++) {
			list = reverseLength(list, position, lengths[i]);
			position += lengths[i];
			position += skipSize;
			position = position % list.length;
			skipSize += 1;
		}
		
		return list;
	}
	
	public static int[] reverseLength(int[] list, int position, int length) {
		
		List<Integer> subList = new ArrayList<Integer>();
		
		int i=position;
		
		while(subList.size() < length) {
			subList.add(0,list[i % list.length]);
			i += 1;
			i = i % list.length;
		}
		
		i=position;
		
		while(subList.size() > 0) {
			list[i % list.length] = subList.remove(0);
			i += 1;
			i = i % list.length;
		}
		
		return list;
	}
	
}
