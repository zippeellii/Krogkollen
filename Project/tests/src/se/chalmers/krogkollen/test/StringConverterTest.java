package se.chalmers.krogkollen.test;

import org.junit.Test;

import se.chalmers.krogkollen.utils.StringConverter;
import android.test.AndroidTestCase;

public class StringConverterTest extends AndroidTestCase {
	public StringConverterTest() {
		super();
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
	public void testConvertStringToFragmentedInt() {
		String string = ":1111:2222:3333:4444:5555:";
		String incorrectString1 = "abc";
		String incorrectString2 = "::";
		String incorrectString3 = "10:";
		String incorrectString4 = "";
		String incorrectString5 = ":10a:";
		String incorrectString6 = ":10";
		
		
		int x = StringConverter.convertStringToFragmentedInt(string, 3);
		assertTrue(x == 3333);
		
		x = StringConverter.convertStringToFragmentedInt(string, 5);
		assertTrue(x == 5555);
		
		boolean exceptionThrown = false;
		try {
			x = StringConverter.convertStringToFragmentedInt(incorrectString1, 1);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		exceptionThrown = false;
		try {
			x = StringConverter.convertStringToFragmentedInt(incorrectString2, 1);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		exceptionThrown = false;
		try {
			x = StringConverter.convertStringToFragmentedInt(incorrectString3, 1);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		exceptionThrown = false;
		try {
			x = StringConverter.convertStringToFragmentedInt(incorrectString4, 1);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		exceptionThrown = false;
		try {
			x = StringConverter.convertStringToFragmentedInt(incorrectString5, 1);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		exceptionThrown = false;
		try {
			x = StringConverter.convertStringToFragmentedInt(incorrectString6, 1);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		exceptionThrown = false;
		try {
			x = StringConverter.convertStringToFragmentedInt(string, 0);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		exceptionThrown = false;
		try {
			x = StringConverter.convertStringToFragmentedInt(string, 300);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
}
