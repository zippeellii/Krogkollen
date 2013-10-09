package se.chalmers.krogkollen.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;

/**
 * A mockup backend that can be used for testing purposes and faster response times
 * 
 * @author Oskar Karrman
 * 
 */
public class BackendMockup implements IBackend {

	ArrayList<IPub>	pubs			= new ArrayList<IPub>();
	boolean			throwNoBackend	= false;
	boolean			throwNotFound	= false;

	/**
	 * Instantiates a new BackendMockup
	 * 
	 * @param exceptions if 1: always throw NoBackendAccessException if 2: always throw
	 *            NotFoundInBackendException
	 */
	public BackendMockup(int exceptions) {
		this.pubs.add(new Pub());

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

	private void throwNoBackend() throws NoBackendAccessException {
		if (throwNoBackend)
			throw new NoBackendAccessException(
					"No backend access exception, thrown from mockup backend", 1337);
	}

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
	public Date getLatestUpdatedTimestamp(IPub pub) throws NotFoundInBackendException,
			NoBackendAccessException {
		this.throwNoBackend();
		this.throwNotFound();

		return new Date(2010, 11, 25);
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
