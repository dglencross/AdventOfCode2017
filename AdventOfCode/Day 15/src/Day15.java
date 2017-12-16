
public class Day15 {

	public static int solution1(int iterations, Long startA, Long startB) {
		int count = 0;
		
		Generator genA = new Generator(16807L);
		Generator genB = new Generator(48271L);
		
		for(int i=0; i < iterations; i++) {
			startA = genA.nextValue(startA);
			startB = genB.nextValue(startB);
			
			if (genA.getBinaryStringLastSixteen(startA).equals(genB.getBinaryStringLastSixteen(startB))) {
				count += 1;
			}
		}
		
		return count;
	}
	
	public static int solution2(int iterations, Long startA, Long startB) {
		int count = 0;
		
		Generator genA = new Generator(16807L,4);
		Generator genB = new Generator(48271L,8);
		
		for(int i=0; i < iterations; i++) {
			startA = genA.nextValueWithMultiple(startA);
			startB = genB.nextValueWithMultiple(startB);
			
			if (genA.getBinaryStringLastSixteen(startA).equals(genB.getBinaryStringLastSixteen(startB))) {
				count += 1;
			}
		}
		
		return count;
	}
	
}
