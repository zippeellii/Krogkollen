package se.chalmers.krogkollen.utils;

public interface IObservable {
	
	/**
	 * Adds an observer to the observable object.
	 * @param observer the new observer
	 */
	public void addObserver(IObserver observer);
	
	/**
	 * Removes and observer from the observable object.
	 * @param observer	the observer that will be removed
	 */
	public void removeObserver(IObserver observer);
}
