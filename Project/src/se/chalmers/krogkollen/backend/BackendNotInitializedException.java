package se.chalmers.krogkollen.backend;

/**
 * Exception which should be thrown if a backend has not been initialized
 * 
 * @author Oskar Karrman
 *
 */
public class BackendNotInitializedException extends Exception {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -5054620912828459605L;
	
	/**
	 * Create the exception without additional information
	 */
	public BackendNotInitializedException() {
		super();
	}
	
	/**
	 * Create the exception with the specified message
	 * 
	 * @param message
	 */
	public BackendNotInitializedException(String message) {
		super(message);
	}
}

	
