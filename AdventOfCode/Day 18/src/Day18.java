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
	
	public static Long run(List<String> instructions) {
		Program18 p = new Program18();
		
		Status18 status = p.Run(instructions);
		
		return status.sent;
	}
	
}
