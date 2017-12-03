
public class Pair {
	
	private int row;
	private int column;
	
	public Pair(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public void print() {
		System.out.println(row + "," + column);
	}
}
