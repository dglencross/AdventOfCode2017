
public class Location {

	private Long x;
	private Long y;
	private Long z;
	
	public Location(Long x, Long y, Long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Location)) {
			return false;
		}
		
		Location longObj = (Location)obj;
		
		return this.x.equals(longObj.x) && this.y.equals(longObj.y) && z.equals(longObj.z);
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash += x.hashCode() * 23;
		hash += y.hashCode() * 23;
		hash += y.hashCode() * 23;
		
		return hash;
	}
}
