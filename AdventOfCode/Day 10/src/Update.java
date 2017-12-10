
public class Update {
	public int position;
	public int skipSize;
	public int[] list;
	
	public Update(int[] list, int position, int skipSize) {
		this.list = list;
		this.position = position;
		this.skipSize = skipSize;
	}
}
