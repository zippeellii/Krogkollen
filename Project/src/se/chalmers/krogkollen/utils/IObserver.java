package se.chalmers.krogkollen.utils;

/**
 * Interface describing something that can observe an IObservable object
 */
public interface IObserver {
	/**
	 * Updates the observer.
	 */
	public void update();
}
