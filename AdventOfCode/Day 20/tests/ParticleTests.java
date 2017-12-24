import org.junit.Assert;
import org.junit.Test;


public class ParticleTests {

	@Test
	public void test_manhattan_distance() {
		Particle p = new Particle(3, 0, 0, 2, 0, 0, -1, 0, 0);
		
		Assert.assertEquals((Long)3L, p.getDistanceFromZero());
	}
	
	@Test
	public void test_manhattan_distance_2() {
		Particle p = new Particle(3, 0, -2, 2, 0, 0, -1, 0, 0);
		
		Assert.assertEquals((Long)5L, p.getDistanceFromZero());
	}
	
	@Test
	public void test_manhattan_distance_3() {
		Particle p = new Particle(3, 5, -2, 2, 0, 0, -1, 0, 0);
		
		Assert.assertEquals((Long)10L, p.getDistanceFromZero());
	}
	
	@Test
	public void test_tick() {
		Particle p = new Particle(3, 0, 0, 2, 0, 0, -1, 0, 0);
		
		p.tick();
		Assert.assertEquals("400100-100", p.getState());
		
		p.tick();
		Assert.assertEquals("400000-100", p.getState());
		
		p.tick();
		Assert.assertEquals("300-100-100", p.getState());
	}
	
	@Test
	public void test_tick_2() {
		Particle p = new Particle(4, 0, 0, 0, 0, 0, -2, 0, 0);
		
		p.tick();
		Assert.assertEquals("200-200-200", p.getState());
		
		p.tick();
		Assert.assertEquals("-200-400-200", p.getState());
		
		p.tick();
		Assert.assertEquals("-800-600-200", p.getState());
	}
	
}
