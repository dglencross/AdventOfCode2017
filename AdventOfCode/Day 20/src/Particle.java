
public class Particle {

	private boolean removed;
	private Long positionX;
	public Long getPositionX() {
		return positionX;
	}

	public Long getPositionY() {
		return positionY;
	}

	public Long getPositionZ() {
		return positionZ;
	}

	public Long getVelocityX() {
		return velocityX;
	}

	public Long getVelocityY() {
		return velocityY;
	}

	public Long getVelocityZ() {
		return velocityZ;
	}

	public Long getAccelX() {
		return accelX;
	}

	public Long getAccelY() {
		return accelY;
	}

	public Long getAccelZ() {
		return accelZ;
	}

	private Long positionY;
	private Long positionZ;
	
	private Long velocityX;
	private Long velocityY;
	private Long velocityZ;
	
	private Long accelX;
	private Long accelY;
	private Long accelZ;
	
	public Particle(Long positionX, Long positionY, Long positionZ,
					Long velocityX, Long velocityY, Long velocityZ,
					Long accelX, Long accelY, Long accelZ) {
		
		this.positionX = positionX;
		this.positionY = positionY;
		this.positionZ = positionZ;
		
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityZ = velocityZ;
		
		this.accelX = accelX;
		this.accelY = accelY;
		this.accelZ = accelZ;
	}
	
	public Particle(int a, int b, int c, int d, int e, int f, int g, int h, int i) {
		this(Long.valueOf(a),Long.valueOf(b),Long.valueOf(c),Long.valueOf(d),Long.valueOf(e)
				,Long.valueOf(f),Long.valueOf(g),Long.valueOf(h),Long.valueOf(i));
	}
	
	public Long getDistanceFromZero() {
		return Math.abs(positionX) + Math.abs(positionY) + Math.abs(positionZ);
	}
	
	public Long getAcceleration() {
		return Math.abs(accelX) + Math.abs(accelY) + Math.abs(accelZ);
	}
	
	public Location getLocation() {
		return new Location(positionX, positionY, positionZ);
	}
	
	public void tick() {
		velocityX += accelX;
		velocityY += accelY;
		velocityZ += accelZ;
		
		positionX += velocityX;
		positionY += velocityY;
		positionZ += velocityZ;
	}
	
	public String getState() {
		return positionX.toString() + positionY.toString() + positionZ.toString() +
				velocityX.toString() + velocityY.toString() + velocityZ.toString() +
				accelX.toString() + accelY.toString() + accelZ.toString();
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	
}
