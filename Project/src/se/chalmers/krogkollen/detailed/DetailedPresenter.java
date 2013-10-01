package se.chalmers.krogkollen.detailed;

import android.content.Context;
import android.content.SharedPreferences;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.pub.IPub;

/**
 * A presenter class for the detailed view of a pub
 * 
 * @author Oskar Karrman
 *
 */
public class DetailedPresenter implements IDetailedPresenter {

    private IView view;

	@Override
	public void setView(IView view) {
        this.view=view;
	}

	@Override
	public void ratingChanged(IPub pub, int rating) {
	}

}
