package se.chalmers.krogkollen;

/**
 * Interface for a View class in the MVP design pattern.
 * 
 * @author Oskar Kärrman
 *
 */
public interface IView {
	
	/**
	 * Navigates to another view
	 * 
	 * @param destination
	 */
	public abstract void navigate(Class<?> destination);
	
	public abstract void showErrorMessage(String message);
}
