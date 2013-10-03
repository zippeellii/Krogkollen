package se.chalmers.krogkollen.detailed;

import android.content.Context;
import android.content.SharedPreferences;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.backend.Backend;
import se.chalmers.krogkollen.backend.IBackend;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.map.MapActivity;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.utils.EQueueIndicator;

/**
 * A presenter class for the detailed view of a pub
 * 
 * @author Oskar Karrman
 *
 */
public class DetailedPresenter implements IDetailedPresenter {

    private DetailedActivity view;
    private IPub pub;

	@Override
	public void setView(IView view) {

        this.view= (DetailedActivity)view;

	}

    public void setPub(String pubID){
        pub = PubUtilities.getInstance().getPub(pubID);
    }

	@Override
	public void ratingChanged(int rating) throws NotFoundInBackendException, NoBackendAccessException{

        if(rating==1){
            if (view.getSharedPreferences(pub.getID(), 0).getInt(pub.getID(), 0)==1){
                view.setThumbs(0);

                Backend.getInstance().removeRatingVote(pub, 1);

                saveThumbState(0);

            }else{
                view.setThumbs(1);

                Backend.getInstance().addRatingVote(pub, 1);

                saveThumbState(1);

            }
        }

        else if(rating==-1){
            if (view.getSharedPreferences(pub.getID(), 0).getInt(pub.getID(), 0)==-1){
                view.setThumbs(0);

                Backend.getInstance().removeRatingVote(pub, -1);

                saveThumbState(0);

            }else{
                view.setThumbs(-1);

                Backend.getInstance().addRatingVote(pub, -1);

                saveThumbState(-1);
            }
        }else{
            view.setThumbs(rating);

            Backend.getInstance().addRatingVote(pub, rating);

            saveThumbState(rating);
        }


    }

    public void saveThumbState(int thumb){
        SharedPreferences.Editor editor = view.getSharedPreferences(pub.getID(), 0).edit();
        editor.putInt(pub.getID(), thumb);
        editor.commit();
    }

    public String convertOpeningHours(int hour){
        if(hour / 10 ==0){
            return "0"+hour;
        }
        return ""+hour;
    }

    public EQueueIndicator getQueueTime(){
        switch(pub.getQueueTime()) {
            case 1:
                return EQueueIndicator.GREEN;
            case 2:
                return EQueueIndicator.YELLOW;
            case 3:
                return EQueueIndicator.RED;
            default:
                return EQueueIndicator.GREY;
        }
    }

    public void getText(){
        view.updateText(pub.getName(), pub.getDescription(), convertOpeningHours(pub.getTodaysOpeningHour()) + " - " +
                (convertOpeningHours(pub.getTodaysClosingHour())), ""+pub.getAgeRestriction() + " År", ""+pub.getEntranceFee()
                + " :-");
    }

    public void getThumbs(){
        view.setThumbs(view.getSharedPreferences(pub.getID(), 0).getInt(pub.getID(), 0));
    }

}
