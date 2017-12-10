
import org.junit.Assert;
import org.junit.Test;


public class Day10Tests {

	private int[] list256 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255};
	
	
	@Test
	public void test_reverse_1() {
		
		int[] list = {0,1,2,3,4};
		
		int[] reversed = Day10.reverseLength(list, 0, 3);
		
		Assert.assertEquals(2,reversed[0]);
		Assert.assertEquals(1,reversed[1]);
		Assert.assertEquals(0,reversed[2]);
		Assert.assertEquals(3,reversed[3]);
		Assert.assertEquals(4,reversed[4]);
		
	}
	
	@Test
	public void test_reverse_2() {
		
		int[] list = {0,1,2,3,4};
		
		int[] reversed = Day10.reverseLength(list, 1, 3);
		
		Assert.assertEquals(0,reversed[0]);
		Assert.assertEquals(3,reversed[1]);
		Assert.assertEquals(2,reversed[2]);
		Assert.assertEquals(1,reversed[3]);
		Assert.assertEquals(4,reversed[4]);
		
	}
	
	@Test
	public void test_reverse_wrap() {
		
		int[] list = {0,1,2,3,4};
		
		int[] reversed = Day10.reverseLength(list, 3, 4);
		
		// 4 3 2 1 0
		
		Assert.assertEquals(4,reversed[0]);
		Assert.assertEquals(3,reversed[1]);
		Assert.assertEquals(2,reversed[2]);
		Assert.assertEquals(1,reversed[3]);
		Assert.assertEquals(0,reversed[4]);
		
	}
	
	@Test
	public void test_dummy_lengths() {
		int[] list = {0,1,2,3,4};
		int[] lengths = {3, 4, 1, 5};
		
		int[] reversed = Day10.updateList(list, lengths);
		
		Assert.assertEquals(3,reversed[0]);
		Assert.assertEquals(4,reversed[1]);
		Assert.assertEquals(2,reversed[2]);
		Assert.assertEquals(1,reversed[3]);
		Assert.assertEquals(0,reversed[4]);
	}
	
	@Test
	public void test_dummy_input() {
		int[] list = {0,1,2,3,4};
		int[] lengths = {3, 4, 1, 5};
		
		Assert.assertEquals(12, Day10.firstTwoValuesMultiplied(list, lengths));
	}
	
	@Test
	public void solution_1() {
		int[] lengths = {106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36};
		
		Assert.assertEquals(11413, Day10.firstTwoValuesMultiplied(list256, lengths));
	}
	
	@Test
	public void test_modify_lengths() {
		String lengths = "1,2,3";
		
		int[] output = Day10.modifyLengths(lengths);
		
		// 49,44,50,44,51,17,31,73,47,23
		Assert.assertEquals(49,output[0]);
		Assert.assertEquals(44,output[1]);
		Assert.assertEquals(50,output[2]);
		Assert.assertEquals(44,output[3]);
		Assert.assertEquals(51,output[4]);
		Assert.assertEquals(17,output[5]);
		Assert.assertEquals(31,output[6]);
		Assert.assertEquals(73,output[7]);
		Assert.assertEquals(47,output[8]);
		Assert.assertEquals(23,output[9]);
	}
	
	@Test
	public void test_part_2_example_1() {
		String lengths = "";
		Assert.assertEquals("a2582a3a0e66e6e86e3812dcb672a272", Day10.knot(list256, lengths));
	}
	
	@Test
	public void test_part_2_example_2() {
		String lengths = "AoC 2017";
		Assert.assertEquals("33efeb34ea91902bb2f59c9920caa6cd", Day10.knot(list256, lengths));
	}
	
	@Test
	public void test_part_2_example_3() {
		String lengths = "1,2,3";
		Assert.assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", Day10.knot(list256, lengths));
	}
	
	@Test
	public void test_part_2_example_4() {
		String lengths = "1,2,4";
		Assert.assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10.knot(list256, lengths));
	}
	
	@Test
	public void test_convert_block_to_dense_value() {
		
		int[] block = {65 , 27 , 9 , 1 , 4 , 3 , 40 , 50 , 91 , 7 , 6 , 0 , 2 , 5 , 68 , 22};
		int output = Day10.convertBlockToDenseValue(block);
		
		Assert.assertEquals(64, output);
	}
	
	@Test
	public void solution_2() {
		String lengths = "106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36";
		
		Assert.assertEquals("7adfd64c2a03a4968cf708d1b7fd418d", Day10.knot(list256, lengths));
	}
}
