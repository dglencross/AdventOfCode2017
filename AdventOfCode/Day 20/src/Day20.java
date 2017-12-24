import java.util.ArrayList;
import java.util.List;


public class Day20 {

	public static int run(List<String> lines, int ticks) {
		
		List<Particle> particles = new ArrayList<>();

		for (String line : lines) {
			particles.add(parseParticle(line));
		}
		
		Particle lowestP = particles.get(0);
		int lowestValue = Integer.MAX_VALUE;
		
		for(Particle p : particles) {
			for (int i=0; i < ticks; i++) {
				p.tick();
			}
		}
		
		for (int i=0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			if (p.getDistanceFromZero() <= lowestP.getDistanceFromZero()) {
				lowestP = p;
				lowestValue = i;
			}
		}
		
		return lowestValue;
	}
	
	public static Particle parseParticle(String line) {
		String[] trios = line.split(", ");
		
		for (int i=0;i<trios.length;i++) {
			String part = trios[i];
			part = part.replace("p", "");
			part = part.replace("=", "");
			part = part.replace("<", "");
			part = part.replace(">", "");
			part = part.replace("v", "");
			part = part.replace("a", "");
			part = part.replace(" ", "");
			
			trios[i] = part;
		}
		
		Long posX = Long.parseLong(trios[0].split(",")[0]);
		Long posY = Long.parseLong(trios[0].split(",")[1]);
		Long posZ = Long.parseLong(trios[0].split(",")[2]);
		
		Long velX = Long.parseLong(trios[1].split(",")[0]);
		Long velY = Long.parseLong(trios[1].split(",")[1]);
		Long velZ = Long.parseLong(trios[1].split(",")[2]);
		
		Long accX = Long.parseLong(trios[2].split(",")[0]);
		Long accY = Long.parseLong(trios[2].split(",")[1]);
		Long accZ = Long.parseLong(trios[2].split(",")[2]);
		
		return new Particle(posX, posY, posZ, velX, velY, velZ, accX, accY, accZ);
	}
	
}
