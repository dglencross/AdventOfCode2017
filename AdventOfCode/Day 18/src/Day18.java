import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day18 {

	public static Long GetInteger(Map<String, Long> registry, String input) {
		Long divisor = 0L;
		
		try {
			divisor = Long.valueOf(input);
		} catch (Exception e) {
			divisor = registry.get(input);
			if (divisor == null) divisor = 0L;
		}
		
		return divisor;
	}
	
	public static Long part1(List<String> instructions) {
		Program18 p = new Program18();
		
		Status18 status = p.Run(instructions);
		
		return Long.valueOf(status.getSent());
	}
	
	public static int part2(List<String> instructions) {
		
		boolean finished = false;
		
		Program18 p0 = new Program18();
		Program18 p1 = new Program18();
		
		Map<String,Long> registry0 = new HashMap<String, Long>();
		registry0.put("p", 0L);
		Map<String,Long> registry1 = new HashMap<String, Long>();
		registry1.put("p", 1L);
		
		int count = 0;
		
		Status18 s0 = p0.Run(instructions, 0, registry0, 0L);
		System.out.println("p0 sent " + s0.getSent());
		p1.AddToQueue(s0.getSent());
		
		Status18 s1 = p1.Run(instructions, 0, registry1, 0L);
		System.out.println("p1 sent " + s1.getSent());
		count += 1;
		p0.AddToQueue(s1.getSent());
		
		while (!finished) {
			
			s0 = p0.Run(instructions, s0.getPosition(), s0.getRegistry(), s0.getLastPlayedFreq());
			if (!s0.isWaiting()) {
				System.out.println("p0 sent " + s0.getSent());
				p1.AddToQueue(s0.getSent());
			}
			
			s1 = p1.Run(instructions, s1.getPosition(), s1.getRegistry(), s1.getLastPlayedFreq());
			if (!s1.isWaiting()) {
				System.out.println("p1 sent " + s1.getSent());
				count += 1;
				p0.AddToQueue(s1.getSent());
			}
			
			if (count > 10) {
				finished = true;
			}
			
			if (s0.isWaiting() && s1.isWaiting() && p0.getQueue().isEmpty() && p1.getQueue().isEmpty()) {
				finished = true;
			}
			
		}
		
		return count;
	}
	
}
