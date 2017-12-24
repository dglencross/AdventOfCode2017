import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program18 {
	
	private List<String> queue;
	
	public Program18() {
		queue = new ArrayList<String>();
	}
	
	public Status18 Run(List<String> instructions) {
		return Run(instructions, 0, new HashMap<>(), 0L);
	}
	
	public void AddToQueue(String x) {
		queue.add(x);
	}
	
	public List<String> getQueue() {
		return this.queue;
	}
	
	public Status18 Run(List<String> instructions, int location, Map<String, Long> registry, Long lastPlayedFreq) {
		
		boolean finished = false;
		
		while(!finished) {
			
			String i = instructions.get(location);
			boolean moved = false;
			
			String[] parts = i.split(" ");
			String type = parts[0];
			String register = parts[1];
			
			if ("set".equals(type)) {
				registry.put(register, Day18.GetInteger(registry, parts[2]));
			} else if ("add".equals(type)) {
				Long currentValue = Day18.GetInteger(registry, register);
				registry.put(register, currentValue + Day18.GetInteger(registry, parts[2]));
			} else if ("mul".equals(type)) {
				Long X = Day18.GetInteger(registry, register);
				Long Y = Day18.GetInteger(registry, parts[2]);
				registry.put(register, X * Y);
			} else if ("mod".equals(type)) {
				Long currentValue = Day18.GetInteger(registry, register);
				registry.put(register, currentValue % Day18.GetInteger(registry, parts[2]));
			} else if ("snd".equals(type)) {
				// play sound
				
				lastPlayedFreq = Day18.GetInteger(registry, parts[1]);
				
				if (lastPlayedFreq != null) {
					return new Status18(lastPlayedFreq.toString(), location + 1, instructions, registry, false, lastPlayedFreq);
				}
				
			} else if ("rcv".equals(type)) {
				if (this.queue.isEmpty()) {
					return new Status18(lastPlayedFreq.toString(), location, instructions, registry, true, lastPlayedFreq);
				}
				
				String r = parts[1];
				Long value = Long.valueOf(this.queue.remove(0));
				registry.put(r, value);
			}
			else if ("jgz".equals(type)) {
				Long currentValue = Day18.GetInteger(registry, register);
				if (currentValue != null && currentValue > 0) {
					location += Day18.GetInteger(registry, parts[2]);
					moved = true;
				}
			}
			
			if (!moved) location += 1;
			
			if (location < 0 || location >= instructions.size()) {
				finished = true;
			}
		}
		
		throw new RuntimeException("Terminate!");
//		return new Status18(lastPlayedFreq.toString(), location, instructions, registry, true);
	}
}
