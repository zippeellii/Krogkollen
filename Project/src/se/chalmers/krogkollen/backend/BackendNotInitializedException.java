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
	
	// TODO javadoc
	public BackendNotInitializedException() {
		super();
	}
	
	// TODO javadoc
	public BackendNotInitializedException(String message) {
		super(message);
	}
}

	
