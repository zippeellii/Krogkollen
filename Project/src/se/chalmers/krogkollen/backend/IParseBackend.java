package se.chalmers.krogkollen.backend;
import java.util.Date;
import se.chalmers.krogkollen.pub.IPub;

/**
 * A backend interface for a parse.com database
 * 
 * @author Jonathan Nilsfors
 * @author Oskar Karrman
 */
public interface IParseBackend extends IBackend {
	
	/**
	 * Returns the number of positive ratings for a requested pub
	 * 
	 * @param pub the source
	 * @return the requested source
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException
	 */
	public int getPositiveRating(IPub pub) throws NotFoundInBackendException, NoBackendAccessException;

	/**
	 * Returns the number of negative ratings for a requested pub
	 * 
	 * @param pub the source 
	 * @return the requested value
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException 
	 */
	public int getNegativeRating(IPub pub) throws NotFoundInBackendException, NoBackendAccessException;
	
	/**
	 * Returns the date of which the pub was latest updated
	 * 
	 * @param pub the requested pub
	 * @return latest update date
	 * @throws NotFoundInBackendException
	 * @throws NoBackendAccessException 
	 */
	public Date getLatestUpdatedTimestamp(IPub pub) throws NotFoundInBackendException, NoBackendAccessException;
}
