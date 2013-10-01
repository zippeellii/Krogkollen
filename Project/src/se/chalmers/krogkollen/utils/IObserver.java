package se.chalmers.krogkollen.utils;

public interface IObserver {
	
	public enum Status {FIRST_LOCATION, 
						NORMAL_UPDATE, 
						ALL_DISABLED, 
						NET_DISABLED, 
						GPS_DISABLED,
						ALL_ENABLED,
						NET_ENABLED, 
						GPS_ENABLED};
	
	/**
	 * Updates the observer.
	 */
	public void update(Status status);
}
