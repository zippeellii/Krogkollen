package se.chalmers.krogkollen.test;

import org.junit.Test;

import se.chalmers.krogkollen.pub.*;
import android.test.AndroidTestCase;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */

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
