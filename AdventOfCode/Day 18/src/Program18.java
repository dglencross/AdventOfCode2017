import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program18 {
	
	public Status18 Run(List<String> instructions) {
		Map<String, Long> registry = new HashMap<>();
		
		boolean finished = false;
		int location = 0;
		
		Long lastPlayedFreq = 0L;
		
		while(!finished) {
			
			String i = instructions.get(location);
			boolean moved = false;
			
			String[] parts = i.split(" ");
			String type = parts[0];
			String register = parts[1];
			
			if ("set".equals(type)) {
				registry.put(register, Day18.GetInteger(registry, parts[2]));
			} else if ("add".equals(type)) {
				Long currentValue = registry.get(register);
				registry.put(register, currentValue + Day18.GetInteger(registry, parts[2]));
			} else if ("mul".equals(type)) {
				Long X = registry.get(register);
				Long Y = Day18.GetInteger(registry, parts[2]);
				
				if (X == null) {
					X = 0L;
				}
				registry.put(register, X * Y);
			} else if ("mod".equals(type)) {
				Long currentValue = registry.get(register);
				registry.put(register, currentValue % Day18.GetInteger(registry, parts[2]));
			} else if ("snd".equals(type)) {
				// play sound
				lastPlayedFreq = registry.get(register);
			} else if ("rcv".equals(type)) {
				Long currentValue = registry.get(register);
				if (currentValue != 0) {
					return new Status18(lastPlayedFreq, location, instructions);
				}
			}
			else if ("jgz".equals(type)) {
				Long currentValue = registry.get(register);
				if (currentValue > 0) {
					location += Day18.GetInteger(registry, parts[2]);
					moved = true;
				}
			} else {
				throw new RuntimeException("Unrecognised instruction!");
			}
			
			if (!moved) location += 1;
			
			if (location < 0 || location >= instructions.size()) {
				finished = true;
			}
		}
		
		return new Status18(lastPlayedFreq, location, instructions);
	}
}
