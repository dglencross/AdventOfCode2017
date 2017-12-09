import java.util.ArrayList;
import java.util.List;


public class Tower {
	
	private String name;
	private int weight;
	private List<String> children;
	private Tower parent;
	
	public Tower(String line) {
		String[] parts = line.split(" ");
		this.name = parts[0];
		this.weight = parseNumber(parts[1]);
		this.children = new ArrayList<String>();
		if (line.contains("->")) {
			for(int i=3; i<parts.length; i++) {
				this.children.add(parts[i].replace(",",""));
			}
		}
	}
	
	private int parseNumber(String number) {
		return Integer.valueOf( number.replace("(", "").replace(")", "")  );
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public List<String> getChildren() {
		return this.children;
	}
	
	public Tower getParent() {
		return this.parent;
	}

	public void setParent(Tower t) {
		this.parent = t;
	}
}
