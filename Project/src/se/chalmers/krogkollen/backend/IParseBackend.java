package se.chalmers.krogkollen.backend;
import java.util.Date;
import se.chalmers.krogkollen.pub.IPub;

/**
 * A backend interface for a parse.com database
 * @author Jonathan Nilsfors
 *
 */
public interface IParseBackend extends IBackend {
	
	/**
	 * Returns the number of positive ratings for a requested pub
	 * @param pub the source
	 * @return the requested source
	 */
	public int getPositiveRating(IPub pub);

	/**
	 * Returns the number of negative ratings for a requested pub
	 * @param pub the source 
	 * @return the requested value
	 */
	public int getNegativeRating(IPub pub);
	
	/**
	 * Returns the date of which the pub was latest updated
	 * @param pub the requested pub
	 * @return latest update date
	 */
	public Date getLatestUpdatedTimestamp(IPub pub) throws NotFoundInBackendException;

}
