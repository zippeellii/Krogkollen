package se.chalmers.krogkollen.detailed;

import android.content.SharedPreferences;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.backend.Backend;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;

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

	public void setPub(String pubID) throws NoBackendAccessException, NotFoundInBackendException{
		pub = PubUtilities.getInstance().getPub(pubID);
		Backend.getInstance().updatePubLocally(pub);
	}

	@Override
	public void ratingChanged(int rating) throws NotFoundInBackendException, NoBackendAccessException{

		if(rating==1){
			if (view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0)==1){
				view.setThumbs(0);

				Backend.getInstance().removeRatingVote(pub, 1);
				pub.setPositiveRating(pub.getPositiveRating() - 1);

				saveThumbState(0);

			}else if(view.getSharedPreferences(pub.getID(), 0).getInt("thumb",0)==-1){
				view.setThumbs(1);

				Backend.getInstance().removeRatingVote(pub, -1);
				pub.setNegativeRating(pub.getNegativeRating() - 1);

				Backend.getInstance().addRatingVote(pub, 1);
				pub.setPositiveRating(pub.getPositiveRating() + 1);

				saveThumbState(1);
			}else{
				view.setThumbs(1);

				Backend.getInstance().addRatingVote(pub, 1);
				pub.setPositiveRating(pub.getPositiveRating() + 1);

				saveThumbState(1);
			}
		}

		else if(rating==-1){
			if (view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0)==-1){
				view.setThumbs(0);

				Backend.getInstance().removeRatingVote(pub, -1);
				pub.setNegativeRating(pub.getNegativeRating() - 1);

				saveThumbState(0);

			}else if(view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0)==1){
				view.setThumbs(-1);

				Backend.getInstance().removeRatingVote(pub, 1);
				pub.setPositiveRating(pub.getPositiveRating() - 1);
				Backend.getInstance().addRatingVote(pub, -1);
				pub.setNegativeRating(pub.getNegativeRating() + 1);

				saveThumbState(-1);
			}
			else{
				view.setThumbs(-1);

				Backend.getInstance().addRatingVote(pub, -1);
				pub.setNegativeRating(pub.getNegativeRating() + 1);

				saveThumbState(-1);
			}
		}else{
			view.setThumbs(rating);

			Backend.getInstance().addRatingVote(pub, rating);
			if(rating > 0){
				pub.setPositiveRating(pub.getPositiveRating() + 1);
			}
			else{
				pub.setNegativeRating(pub.getNegativeRating() + 1);
			}


			saveThumbState(rating);
		}


	}

    public void saveFavoriteState(){
        SharedPreferences.Editor editor = view.getSharedPreferences(pub.getID(), 0).edit();
        editor.putBoolean("star", !(view.getSharedPreferences(pub.getID(), 0).getBoolean("star", true)));
        editor.commit();
    }

	public void saveThumbState(int thumb){
		SharedPreferences.Editor editor = view.getSharedPreferences(pub.getID(), 0).edit();
		editor.putInt("thumb", thumb);
		editor.commit();
	}

	public String convertOpeningHours(int hour){
		if(hour / 10 ==0){
			return "0"+hour;
		}
		return ""+hour;
	}

	public void getQueueTime(){
		view.updateQueueIndicator(pub.getQueueTime());
	}

	public void getText(){
		view.updateText(pub.getName(), pub.getDescription(), convertOpeningHours(pub.getTodaysOpeningHour()) + " - " +
				(convertOpeningHours(pub.getTodaysClosingHour())), ""+pub.getAgeRestriction() + " År", ""+pub.getEntranceFee()
				+ " :-");
	}

	public void getThumbs(){
		view.setThumbs(view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0));
	}

    public void getFavoriteStar(){
        view.updateStar(view.getSharedPreferences(pub.getID(), 0).getBoolean("star", true));
    }

	public void getVotes() throws NoBackendAccessException, NotFoundInBackendException{
		view.updateVotes(""+ pub.getPositiveRating(), ""+ pub.getNegativeRating());
	}

    public void updateInfo() throws NoBackendAccessException, NotFoundInBackendException{
        Backend.getInstance().updatePubLocally(pub);
    }
}
