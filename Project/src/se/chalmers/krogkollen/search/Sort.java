package se.chalmers.krogkollen.search;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.krogkollen.pub.IPub;

public abstract class Sort implements ISort{

	public List<IPub> copyPubList(final List<IPub> pubs){
		List<IPub> list_copy = new ArrayList<IPub>();
		for(int i = 0; i < pubs.size(); i++){
			list_copy.add(pubs.get(i));
		}
		return list_copy;
	}
}
