import java.util.Arrays;

public class SpiralMemory {

	public int CalculateSizeOfSquare(int maxValue) {
		
		int maxSize = 1;
		
		// Work out the smallest box that can contain this value
		while(maxSize * maxSize < maxValue) {
			maxSize += 2;
		}
		
		return maxSize;
	}
	
	public Pair getNumberLocation(long number, long[][] grid) {
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid[0].length;j++) {
				if (number == grid[i][j]) {
					return new Pair(i,j);
				}
			}
		}
		throw new RuntimeException("Couldn't find value " + number);
	}
	
	// int should always lose the decimal e.g. 5 / 2 will give us 2, but
	// we start from 0 so middle of a 5x5 size grid is 2,2
	public int getMiddleValue(int length) {
		return length / 2;
	}
	
	/**
	 * @param gridSize should always be odd
	 * @return grid with allocated numbers
	 */
	public long[][] allocatedMemory(int gridSize) {
		
		long[][] grid = new long[gridSize][gridSize];
		
		int middle = getMiddleValue(gridSize);
		
		grid[middle][middle] = 1;
		
		int step = 1;
		int steppedThatDirection = 0;
		int direction = 1; // 1 = right, 2 up, 3 left, 4 down
		long lastNumberAllocated = 1;
		int stepsToTake = 1;
		int directionChanges = 0;
		
		int row = middle;
		int column = middle;
		
		while (lastNumberAllocated < (gridSize*gridSize)) {
			if (direction == 1) {
				column += step;
				lastNumberAllocated++;
			}
			else if (direction == 2) {
				row -= step;
				lastNumberAllocated++;
			}
			else if (direction == 3) {
				column -= step;
				lastNumberAllocated++;
			}
			else if (direction == 4) {
				row += step;
				lastNumberAllocated++;
			}
			grid[row][column] = lastNumberAllocated;
			steppedThatDirection++;
			if (steppedThatDirection >= stepsToTake) {
				direction = getNextDirection(direction);
				steppedThatDirection = 0;
				directionChanges++;
				if (directionChanges > 1) {
					stepsToTake++;
					directionChanges = 0;
				}
			}
		}
		
		return grid;
		
	}
	
	private int getNextDirection(int direction) {
		if (direction < 4) {
			return direction + 1;
		}
		return 1;
	}

	public void printGrid(long[][] grid) {
		System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
	}
	
}
