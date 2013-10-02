package se.chalmers.krogkollen.backend;

import java.util.List;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;

/**
 * Test for the backend
 * 
 * @author Oskar Karrman
 *
 */
public class ServerTest {
	List<IPub> list = PubUtilities.getInstance().getPubList();
}
