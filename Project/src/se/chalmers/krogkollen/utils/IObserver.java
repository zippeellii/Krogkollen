package se.chalmers.krogkollen.utils;

/**
 * Interface describing something that can observe an IObservable object
 */
public interface IObserver {
	
	public enum Status {FIRST_LOCATION, 
						NORMAL_UPDATE, 
						ALL_DISABLED, 
						NET_DISABLED, 
						GPS_DISABLED};
	
	/**
	 * Updates the observer.
	 */
	public void update(Status status);
}
