package se.chalmers.krogkollen.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.sort.SortByQueueTime;
import android.test.AndroidTestCase;

public class SortTest extends AndroidTestCase {
	List<IPub> pubs = new ArrayList<IPub>();
	
	public SortTest() {
		super();
		
		pubs.add(new Pub("Pub1", "", 51, 52, 1, 1, 1, 1, 1, 1, 0, "1"));
		pubs.add(new Pub("Pub2", "", 51.1, 52, 1, 1, 1, 1, 1, 1, 1, "1"));
		pubs.add(new Pub("Pub3", "", 51, 52.1, 1, 1, 1, 1, 1, 1, 1, "1"));
		pubs.add(new Pub("Pub4", "", 51.1, 52.1, 1, 1, 1, 1, 1, 3, 2, "1"));
		pubs.add(new Pub("Pub5", "", 51, 53, 1, 1, 1, 1, 1, 1, 2, "1"));
		pubs.add(new Pub("Pub6", "", 54, 52, 1, 1, 1, 1, 1, 1, 3, "1"));
		pubs.add(new Pub("Pub7", "", 51, 53, 1, 1, 1, 1, 1, 1, 0, "1"));
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testSortByQueueTime() {
		SortByQueueTime sort = new SortByQueueTime();
		
		List<IPub> sortedList = sort.sortAlgorithm(pubs);
		
		assertTrue(sortedList.get(0).getName().equals("Pub2") || sortedList.get(0).getName().equals("Pub3"));		
		assertTrue(sortedList.get(1).getName().equals("Pub2") || sortedList.get(1).getName().equals("Pub3"));
		assertTrue(sortedList.get(2).getName().equals("Pub4") || sortedList.get(2).getName().equals("Pub5"));
		assertTrue(sortedList.get(3).getName().equals("Pub4") || sortedList.get(3).getName().equals("Pub5"));
		assertTrue(sortedList.get(4).getName().equals("Pub6"));
		assertTrue(sortedList.get(5).getName().equals("Pub1") || sortedList.get(5).getName().equals("Pub7"));
		assertTrue(sortedList.get(6).getName().equals("Pub1") || sortedList.get(6).getName().equals("Pub7"));
	}
}
