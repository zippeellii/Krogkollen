package se.chalmers.krogkollen;

public interface IView {
	public abstract void navigate(Class<?> destination);
	
	public abstract void showErrorMessage(String message);
}
