package se.chalmers.krogkollen.backend;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.OpeningHours;
import se.chalmers.krogkollen.pub.Pub;

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

/**
 * A mockup backend that can be used for testing purposes and faster response times
 * 
 * @author Oskar Karrman
 * 
 */
public class BackendMockup implements IBackend {

	ArrayList<IPub>	pubs			= new ArrayList<IPub>();

	/** If changed to true, always throw NoBackendAccessException */
	public boolean	throwNoBackend	= false;

	/** If changed to true, always throw NotFoundInBackendException */
	public boolean	throwNotFound	= false;

	/**
	 * Instantiates a new BackendMockup
	 * 
	 * @param exceptions if 1: always throw NoBackendAccessException if 2: always throw
	 *            NotFoundInBackendException
	 */
	public BackendMockup(int exceptions) {
		this.pubs.add(new Pub("Name", "Description", 57.6875, 11.97669, 20, 100, new OpeningHours(10, 20), 100, 10, 1, 0, "temp"));

		switch (exceptions) {
			case 1:
				this.throwNoBackend = true;
				break;
			case 2:
				this.throwNotFound = true;
				break;
			default:
				break;
		}
	}

	// Throw NoBackendAccessException
	private void throwNoBackend() throws NoBackendAccessException {
		if (throwNoBackend)
			throw new NoBackendAccessException(
					"No backend access exception, thrown from mockup backend", 1337);
	}

	// Throw NotFoundInBackendException
	private void throwNotFound() throws NotFoundInBackendException {
		if (throwNotFound)
			throw new NotFoundInBackendException(
					"Not found in backend exception, thrown from mockup backend");
	}

	@Override
	public List<IPub> getAllPubs() throws NoBackendAccessException {
		this.throwNoBackend();
		return this.pubs;
	}

	@Override
	public int getQueueTime(IPub pub) throws NoBackendAccessException, NotFoundInBackendException {
		this.throwNoBackend();
		this.throwNotFound();

		return pub.getQueueTime();
	}

	@Override
	public IPub getPubFromID(String id) throws NoBackendAccessException, NotFoundInBackendException {
		this.throwNoBackend();
		this.throwNotFound();

		return pubs.get(0);
	}

	@Override
	public void addRatingVote(IPub pub, int rating) throws NoBackendAccessException,
			NotFoundInBackendException {
		this.throwNoBackend();
		this.throwNotFound();

		if (rating == 1) {
			pubs.get(0).setPositiveRating(pubs.get(0).getPositiveRating() + 1);
		} else if (rating == -1) {
			pubs.get(0).setNegativeRating(pubs.get(0).getNegativeRating() + 1);
		}
	}

	@Override
	public void removeRatingVote(IPub pub, int rating) throws NoBackendAccessException,
			NotFoundInBackendException {
		this.throwNoBackend();
		this.throwNotFound();

		if (rating == 1) {
			pubs.get(0).setPositiveRating(pubs.get(0).getPositiveRating() - 1);
		} else if (rating == -1) {
			pubs.get(0).setNegativeRating(pubs.get(0).getNegativeRating() - 1);
		}
	}

	@Override
	public int getPositiveRating(IPub pub) throws NotFoundInBackendException,
			NoBackendAccessException {
		this.throwNoBackend();
		this.throwNotFound();

		return pubs.get(0).getPositiveRating();
	}

	@Override
	public int getNegativeRating(IPub pub) throws NotFoundInBackendException,
			NoBackendAccessException {
		this.throwNoBackend();
		this.throwNotFound();

		return pubs.get(0).getNegativeRating();
	}

	@Override
	public long getLatestUpdatedTimestamp(IPub pub) throws NotFoundInBackendException,
			NoBackendAccessException {
		this.throwNoBackend();
		this.throwNotFound();

		return pubs.get(0).getQueueTimeLastUpdatedTimestamp();
	}

	@Override
	public void updatePubLocally(IPub pub) throws NoBackendAccessException,
			NotFoundInBackendException {
		this.throwNoBackend();
		this.throwNotFound();

		pub.setNegativeRating(pubs.get(0).getNegativeRating());
		pub.setPositiveRating(pubs.get(0).getPositiveRating());
		pub.setQueueTime(pubs.get(0).getQueueTime());
	}
}
