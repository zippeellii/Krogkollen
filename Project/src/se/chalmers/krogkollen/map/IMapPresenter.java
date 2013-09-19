package se.chalmers.krogkollen.map;

import java.util.List;

import se.chalmers.krogkollen.IPresenter;
import se.chalmers.krogkollen.pub.IPub;

public interface IMapPresenter extends IPresenter {
	public abstract void pubClicked(IPub pub);
	
	public abstract List<IPub> search(String search);
	
	public abstract void refresh();
}
