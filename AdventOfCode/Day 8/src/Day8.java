import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day8 {

	public static int returnLargestValueAfterInstructions(List<String> lines) {
		
		Map<String, Integer> register = new HashMap<String,Integer>();
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		for(String line : lines) {
			Instruction in = new Instruction(line);
			instructions.add(in);
			
			if (!register.containsKey(in.getRegister())) {
				register.put(in.getRegister(), 0);
			}
			if (!register.containsKey(in.getDependentRegister())) {
				register.put(in.getDependentRegister(), 0);
			}
		}
		
		for(Instruction in : instructions) {
			int value = register.get(in.getRegister());
			int dependentValue = register.get(in.getDependentRegister());
			if (isSatisfied(in, dependentValue)) {
				if (in.getIncrease()) {
					value += in.getIncreaseAmount();
				} else {
					value -= in.getIncreaseAmount();
				}
				register.put(in.getRegister(), value);
			}
		}
		
		return register.values().stream().mapToInt(Integer::intValue).max().getAsInt();
	}
	
	public static int returnLargestValueDuringInstructions(List<String> lines) {
		
		int largestValue = 0;
		Map<String, Integer> register = new HashMap<String,Integer>();
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		for(String line : lines) {
			Instruction in = new Instruction(line);
			instructions.add(in);
			
			if (!register.containsKey(in.getRegister())) {
				register.put(in.getRegister(), 0);
			}
			if (!register.containsKey(in.getDependentRegister())) {
				register.put(in.getDependentRegister(), 0);
			}
		}
		
		for(Instruction in : instructions) {
			int value = register.get(in.getRegister());
			int dependentValue = register.get(in.getDependentRegister());
			if (isSatisfied(in, dependentValue)) {
				if (in.getIncrease()) {
					value += in.getIncreaseAmount();
				} else {
					value -= in.getIncreaseAmount();
				}
				register.put(in.getRegister(), value);
				if (value > largestValue) {
					largestValue = value;
				}
			}
		}
		
		return largestValue;
	}
	
	public static Boolean isSatisfied(Instruction in, int value) {
		
		int comparer = in.getModifierAmount();
		
		if (in.getModifier().equals(">")) {
			return value > comparer;
		}
		if (in.getModifier().equals("<")) {
			return value < comparer;
		}
		if (in.getModifier().equals("==")) {
			return value == comparer;
		}
		if (in.getModifier().equals("!=")) {
			return value != comparer;
		}
		if (in.getModifier().equals(">=")) {
			return value >= comparer;
		}
		if (in.getModifier().equals("<=")) {
			return value <= comparer;
		}
		return false;
		
	}
	
}
