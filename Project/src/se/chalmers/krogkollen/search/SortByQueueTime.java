package se.chalmers.krogkollen.search;

import java.util.List;
import se.chalmers.krogkollen.pub.IPub;
/**
 * A class with a method for sorting a List of IPubs by queue time
 * @author Jonathan Nilsfors
 *
 */
public class SortByQueueTime extends Sort {

	@Override
	public List<IPub> sortAlgorithm(final List<IPub> pubs) {
		List<IPub> copyOfPubs = this.copyPubList(pubs);
		for(int i = 1; i < copyOfPubs.size(); i++){
			for(int j = 0; j < copyOfPubs.size()-i; j++){
				if((copyOfPubs.get(j).getQueueTime() > copyOfPubs.get(j+1).getQueueTime()
						&& copyOfPubs.get(j+1).getQueueTime() != 0)
						|| copyOfPubs.get(j).getQueueTime() == 0) {
					IPub temp = copyOfPubs.get(j);
					copyOfPubs.set(j, copyOfPubs.get(j + 1));
					copyOfPubs.set((j+1), temp);
				}
			}
		}
		return copyOfPubs;
	}

}
