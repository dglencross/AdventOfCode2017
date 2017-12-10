import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 {
	
	public static int[] modifyLengths(String lengths) {
		char[] chars = lengths.toCharArray();
		int[] actualLengths = new int[chars.length + 5];
		
		for(int i=0; i<chars.length; i++) {
			actualLengths[i] = chars[i];
		}
		actualLengths[actualLengths.length - 5] = 17;
		actualLengths[actualLengths.length - 4] = 31;
		actualLengths[actualLengths.length - 3] = 73;
		actualLengths[actualLengths.length - 2] = 47;
		actualLengths[actualLengths.length - 1] = 23;
		
		return actualLengths;
	}
	
	public static String knot(int[] list, String lengths) {
		
		int[] asciiLengths = modifyLengths(lengths);
		int position = 0;
		int skipSize = 0;
		
		for(int i=0; i<64;i++) {
			Update update = updateList(list, asciiLengths, position, skipSize);
			
			list = update.list;
			position = update.position;
			skipSize = update.skipSize;
		}
		
		int[] denseHash = convertSparseHashToDenseHash(list);
		
		if (denseHash.length != 16) {
			throw new RuntimeException("denseHash was the wrong size: " + denseHash.length);
		}
		
		StringBuilder builder = new StringBuilder();
		
		for(int i=0;i<denseHash.length;i++) {
			String hex = Integer.toHexString(denseHash[i]);
			if (hex.length() != 2) {
				hex = "0" + hex;
			}
			builder.append(hex);
		}
		
		return builder.toString();
	}
	
	public static int[] convertSparseHashToDenseHash(int[] sparseHash) {
		int[] denseHash = new int[16];
		
		for(int i=0; i<256; i+=16) {
			int[] block = Arrays.copyOfRange(sparseHash, i, i+16);
			denseHash[i / 16] = convertBlockToDenseValue(block);
		}
		
		return denseHash;
	}
	
	public static int convertBlockToDenseValue(int[] block) {
		int total = block[0];
		
		for(int i=1; i<block.length; i++) {
			total = total ^ block[i];
		}
		
		return total;
	}

	public static int firstTwoValuesMultiplied(int[] list, int[] lengths) {
		list = updateList(list, lengths);
		
		return (Integer)list[0] * (Integer)list[1];
	}
	
	public static Update updateList(int[] list, int[] lengths, int position, int skipSize) {
		for(int i=0;i<lengths.length; i++) {
			list = reverseLength(list, position, lengths[i]);
			position += lengths[i];
			position += skipSize;
			position = position % list.length;
			skipSize += 1;
		}
		
		return new Update(list, position, skipSize);
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
