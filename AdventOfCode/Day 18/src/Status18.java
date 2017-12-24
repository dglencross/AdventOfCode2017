import java.util.List;
import java.util.Map;


public class Status18 {

	private String sent;
	private int position;
	private List<String> instructions;
	private Map<String, Long> registry;
	private boolean waiting;
	private Long lastPlayedFreq;
	
	public Status18(String sent, int position, List<String> instructions,
			Map<String, Long> registry, boolean waiting, Long lastPlayedFreq) {
		this.setSent(sent);
		this.setPosition(position);
		this.setInstructions(instructions);
		this.setRegistry(registry);
		this.waiting = waiting;
		this.setLastPlayedFreq(lastPlayedFreq);
	}
	
	public boolean isWaiting() {
		return this.waiting;
	}

	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public Map<String, Long> getRegistry() {
		return registry;
	}

	public void setRegistry(Map<String, Long> registry) {
		this.registry = registry;
	}

	public Long getLastPlayedFreq() {
		return lastPlayedFreq;
	}

	public void setLastPlayedFreq(Long lastPlayedFreq) {
		this.lastPlayedFreq = lastPlayedFreq;
	}
	
}
