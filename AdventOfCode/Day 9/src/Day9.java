public class Day9 {
	
	private static boolean lastCharWasIgnore(char[] chars, int index) {
		if (index == 0) {
			return false;
		}
		
		return chars[index-1] == '!' && !lastCharWasIgnore(chars, index-1);
	}
	
	public static int countGarbage(String line) {
		int garbageLines = 0;
		char[] chars = line.toCharArray();
		int[] indented = new int[chars.length];
		int indent = 0;
		boolean reading = true;
		
		for(int i=0;i<chars.length;i++) {
			if (!lastCharWasIgnore(chars, i)) {
				if (chars[i] == '<' && reading) {
					reading = false;
				} else if (chars[i] == '>') {
					reading = true;
				} else 
				if (reading) {
					if (chars[i] == '{') {
						indent++;
						indented[i] = indent;
					} else 
					if (chars[i] == '}') {
						indent--;
					}
				} else if (chars[i] != '!') {
					garbageLines += 1;
				}
			}
		}
		
		return garbageLines;
	}

	public static int score(String line) {
		
		char[] chars = line.toCharArray();
		int[] indented = new int[chars.length];
		int indent = 0;
		boolean reading = true;
		
		for(int i=0;i<chars.length;i++) {
			if (!lastCharWasIgnore(chars, i)) {
				if (chars[i] == '<') {
					reading = false;
				} else if (chars[i] == '>') {
					reading = true;
				} else 
				if (reading) {
					if (chars[i] == '{') {
						indent++;
						indented[i] = indent;
					} else 
					if (chars[i] == '}') {
						indent--;
					}
				}
			}
		}
		
		int total = 0;
		for(int i : indented) {
			total += i;
		}
		
		return total;
	}
	
	class Node {
		private int score;
		private String line;
		
		public Node(int score, String line) {
			this.score = score;
			this.line = line;
		}
		
		public int getScore() {
			return this.score;
		}
	}
	
}
