import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day20 {
	
	public static int solution2(List<String> lines, int ticks) {
		
		/*
		 * Create the list of particles
		 * During each tick:
		 * 	create a hashmap of Location -> List<Particle>
		 *  at the end of each tick, check if any of the map entries have more than one entry
		 *  delete all those from the original list 
		 */
		
		List<Particle> particles = new ArrayList<>();

		for (String line : lines) {
			particles.add(parseParticle(line));
		}
		
		for (int i=0; i < ticks; i++) {
			List<Particle> toDelete = new ArrayList<>();
			Map<Location, List<Particle>> locations = new HashMap<>();
			for(Particle p : particles) {
				if (!p.isRemoved()) {
					p.tick();
					
					List<Particle> ps = locations.get(p.getLocation());
					if (ps == null) ps = new ArrayList<>();
					ps.add(p);
					
					locations.put(p.getLocation(), ps);
				}
			}
			
			for(Location l : locations.keySet()) {
				if (locations.get(l).size() > 1) {
					toDelete.addAll(locations.get(l));
				}
			}
			
			for (Particle delete : toDelete) {
				particles.get(particles.indexOf(delete)).setRemoved(true);
			}
		}
		
		int count = 0;
		for(Particle p : particles) {
			if (!p.isRemoved()) {
				count ++;
			}
		}
		
		
		return count;
	}

	public static int solution1(List<String> lines, int ticks) {
		
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
