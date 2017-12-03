
public class Day3 {

	private long[][] grid;
	private SpiralMemory memory;
	private int gridSize;
	
	public void allocateMemory(int value) {
		this.memory = new SpiralMemory();
		
		int boxSize = memory.CalculateSizeOfSquare(value);
		this.gridSize = boxSize;
		
		this.grid = memory.allocatedMemory(boxSize);
	}
	
	public long calculateCost(long value) {
		
		long cost = 0;
		Pair location = this.memory.getNumberLocation(value, this.grid);
		
		int middle = this.memory.getMiddleValue(this.gridSize);
		
		cost += Math.abs(location.getColumn() - middle);
		cost += Math.abs(location.getRow() - middle);
		
		return cost;
		
	}
	
}
