package se.chalmers.krogkollen.test;

import org.junit.Test;

import se.chalmers.krogkollen.pub.*;
import android.test.AndroidTestCase;


public class PubTest extends AndroidTestCase {
	public PubTest() {
		super();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testQueueTime() {
		IPub pub = new Pub();
		
		pub.setQueueTime(3);
		assertTrue(pub.getQueueTime() == 3);
		
		pub.setQueueTime(-3);
		assertFalse(pub.getQueueTime() < 0);
	}
}
