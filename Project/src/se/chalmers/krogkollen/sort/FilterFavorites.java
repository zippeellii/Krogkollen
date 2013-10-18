package se.chalmers.krogkollen.sort;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.utils.Preferences;



public class FilterFavorites extends ASort{

	@Override
	public List<IPub> sortAlgorithm(List<IPub> pubs) {
		List <IPub> filteredList = new ArrayList<IPub>();
		for(IPub pub : pubs){
			if(!Preferences.getInstance().loadPreference(pub.getID())){
				filteredList.add(pub);
			}
		}
		return filteredList;
	}

}
