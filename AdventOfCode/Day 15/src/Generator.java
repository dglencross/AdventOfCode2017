
public class Generator {

	private Long factor;
	private int multiple;
	
	public Generator(Long factor) {
		this.factor = factor;
	}
	
	public Generator(Long factor, int multiple) {
		this.factor = factor;
		this.multiple = multiple;
	}
	
	public Long nextValue(Long previousValue) {
		Long next = previousValue * factor;
		
		return next % 2147483647;
	}
	
	public Long nextValueWithMultiple(Long previousValue) {
		Long next = previousValue * factor;
		Long remainder = next % 2147483647;
		
		while (remainder % multiple != 0) {
//			System.out.println(remainder);
			next = remainder * factor;
			remainder = next % 2147483647;
		}
		
		return remainder;
	}
	
	public String getBinaryStringLastSixteen(Long value) {
		String binary = Long.toBinaryString(value);
		
		if (binary.length() >= 16) {
			return binary.substring(binary.length() - 16, binary.length());
		}
		
		return binary;
	}
	
}
