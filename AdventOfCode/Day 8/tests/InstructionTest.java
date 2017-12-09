import org.junit.Assert;
import org.junit.Test;


public class InstructionTest {

	@Test
	public void test_instantiate_increase() {
		Instruction in = new Instruction("b inc 5 if a > 1");
		
		Assert.assertEquals("b", in.getRegister());
		Assert.assertTrue(in.getIncrease());
		Assert.assertEquals(5, in.getIncreaseAmount());
		Assert.assertEquals("a", in.getDependentRegister());
		Assert.assertEquals(">", in.getModifier());
		Assert.assertEquals(1, in.getModifierAmount());
	}
	
	@Test
	public void test_instantiate_decrease() {
		Instruction in = new Instruction("c dec -10 if a >= 1");
		
		Assert.assertEquals("c", in.getRegister());
		Assert.assertFalse(in.getIncrease());
		Assert.assertEquals(-10, in.getIncreaseAmount());
		Assert.assertEquals("a", in.getDependentRegister());
		Assert.assertEquals(">=", in.getModifier());
		Assert.assertEquals(1, in.getModifierAmount());
	}
	
}
