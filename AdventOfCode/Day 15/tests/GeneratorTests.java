import org.junit.Assert;
import org.junit.Test;

public class GeneratorTests {

	@Test
	public void test_dummy_A() {
		Generator genA = new Generator(16807L);
		
		Long startValue = 65L;
		Long value = genA.nextValue(startValue);
		Assert.assertEquals((Long)1092455L, value);
		
		value = genA.nextValue(value);
		Assert.assertEquals((Long)1181022009L, value);
		
		value = genA.nextValue(value);
		Assert.assertEquals((Long)245556042L, value);
		
		value = genA.nextValue(value);
		Assert.assertEquals((Long)1744312007L, value);
		
		value = genA.nextValue(value);
		Assert.assertEquals((Long)1352636452L, value);
	}
	
	@Test
	public void test_dummy_B() {
		Generator genB = new Generator(48271L);
		
		Long startValue = 8921L;
		Long value = genB.nextValue(startValue);
		Assert.assertEquals((Long)430625591L, value);
		
		value = genB.nextValue(value);
		Assert.assertEquals((Long)1233683848L, value);
		
		value = genB.nextValue(value);
		Assert.assertEquals((Long)1431495498L, value);
		
		value = genB.nextValue(value);
		Assert.assertEquals((Long)137874439L, value);
		
		value = genB.nextValue(value);
		Assert.assertEquals((Long)285222916L, value);
	}
	
	@Test
	public void test_binary_16() {
		Generator genA = new Generator(16807L);
		
		String lastSixteen = genA.getBinaryStringLastSixteen(245556042L);
		
		Assert.assertEquals("1110001101001010", lastSixteen);
	}
	
	@Test
	public void test_dummy_A_2() {
		Generator genA = new Generator(16807L,4);
		
		Long startValue = 65L;
		Long value = genA.nextValueWithMultiple(startValue);
		Assert.assertEquals((Long)1352636452L, value);
		
		value = genA.nextValueWithMultiple(value);
		Assert.assertEquals((Long)1992081072L, value);
		
		value = genA.nextValueWithMultiple(value);
		Assert.assertEquals((Long)530830436L, value);
		
		value = genA.nextValueWithMultiple(value);
		Assert.assertEquals((Long)1980017072L, value);
		
		value = genA.nextValueWithMultiple(value);
		Assert.assertEquals((Long)740335192L, value);
	}
	
	@Test
	public void test_dummy_B_2() {
		Generator genA = new Generator(48271L,8);
		
		Long startValue = 8921L;
		Long value = genA.nextValueWithMultiple(startValue);
		Assert.assertEquals((Long)1233683848L, value);
		
		value = genA.nextValueWithMultiple(value);
		Assert.assertEquals((Long)862516352L, value);
		
		value = genA.nextValueWithMultiple(value);
		Assert.assertEquals((Long)1159784568L, value);
		
		value = genA.nextValueWithMultiple(value);
		Assert.assertEquals((Long)1616057672L, value);
		
		value = genA.nextValueWithMultiple(value);
		Assert.assertEquals((Long)412269392L, value);
	}

}
