package se.chalmers.krogkollen.search;

import java.util.List;
import se.chalmers.krogkollen.pub.IPub;
/**
 * A class with a method for sorting a List of IPubs by queue time
 * @author Jonathan Nilsfors
 *
 */
public class SortByQueueTime implements ISort {

	@Override
	public List<IPub> sortAlgorithm(List<IPub> pubs) {
		for(int i = 1; i < pubs.size(); i++){
			for(int j = 0; j < pubs.size()-i; j++){
				if(pubs.get(j).getQueueTime() > pubs.get(j+1).getQueueTime()){
					IPub temp = pubs.get(j);
					pubs.set(j, pubs.get(j + 1));
					pubs.set((j+1), temp);
				}
			}
		}
		return pubs;
	}

}
