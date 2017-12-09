
public class Instruction {

	public Instruction(String line) {
		
		String[] parts = line.split(" ");
		register = parts[0];
		increase = parts[1].equals("inc");
		increaseAmount = Integer.valueOf(parts[2]);
		dependentRegister = parts[4];
		modifier = parts[5];
		modifierAmount = Integer.valueOf(parts[6]);
		
	}
	
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}

	public Boolean getIncrease() {
		return increase;
	}

	public void setIncrease(Boolean increase) {
		this.increase = increase;
	}

	public int getIncreaseAmount() {
		return increaseAmount;
	}

	public void setIncreaseAmount(int increaseAmount) {
		this.increaseAmount = increaseAmount;
	}

	public String getDependentRegister() {
		return dependentRegister;
	}

	public void setDependentRegister(String dependentRegister) {
		this.dependentRegister = dependentRegister;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public int getModifierAmount() {
		return modifierAmount;
	}

	public void setModifierAmount(int modifierAmount) {
		this.modifierAmount = modifierAmount;
	}

	private String register;
	private Boolean increase;
	private int increaseAmount;
	private String dependentRegister;
	private String modifier;
	private int modifierAmount;
}
