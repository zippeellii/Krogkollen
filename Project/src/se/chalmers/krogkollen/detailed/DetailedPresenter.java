package se.chalmers.krogkollen.detailed;

import android.content.SharedPreferences;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.backend.BackendHandler;
import se.chalmers.krogkollen.backend.BackendNotInitializedException;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;
import se.chalmers.krogkollen.utils.StringConverter;

/**
 * A presenter class for the detailed view of a pub
 */
public class DetailedPresenter implements IDetailedPresenter {

    /** The view connected with the Presenter */
	private DetailedActivity view;

    /** The pub that the Presenter holds */
	private IPub pub;

	@Override
	public void setView(IView view) {
		this.view= (DetailedActivity)view;
	}

    @Override
	public void setPub(String pubID) throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException{
		pub = PubUtilities.getInstance().getPub(pubID);
		BackendHandler.getInstance().updatePubLocally(pub);
	}

	// TODO Can this be refactored?
	@Override
	public void ratingChanged(int rating) throws NotFoundInBackendException, NoBackendAccessException, BackendNotInitializedException{

		if(rating==1){
			if (view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0)==1){
				view.setThumbs(0);

				BackendHandler.getInstance().removeRatingVote(pub, 1);
				pub.setPositiveRating(pub.getPositiveRating() - 1);

				saveThumbState(0);

			} else if(view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0)==-1){
				view.setThumbs(1);

				BackendHandler.getInstance().removeRatingVote(pub, -1);
				pub.setNegativeRating(pub.getNegativeRating() - 1);

				BackendHandler.getInstance().addRatingVote(pub, 1);
				pub.setPositiveRating(pub.getPositiveRating() + 1);

				saveThumbState(1);
			} else {
				view.setThumbs(1);

				BackendHandler.getInstance().addRatingVote(pub, 1);
				pub.setPositiveRating(pub.getPositiveRating() + 1);

				saveThumbState(1);
			}
		}

		else if(rating==-1){
			if (view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0)==-1){
				view.setThumbs(0);

				BackendHandler.getInstance().removeRatingVote(pub, -1);
				pub.setNegativeRating(pub.getNegativeRating() - 1);

				saveThumbState(0);

			}else if(view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0)==1){
				view.setThumbs(-1);

				BackendHandler.getInstance().removeRatingVote(pub, 1);
				pub.setPositiveRating(pub.getPositiveRating() - 1);
				BackendHandler.getInstance().addRatingVote(pub, -1);
				pub.setNegativeRating(pub.getNegativeRating() + 1);

				saveThumbState(-1);
			}
			else{
				view.setThumbs(-1);

				BackendHandler.getInstance().addRatingVote(pub, -1);
				pub.setNegativeRating(pub.getNegativeRating() + 1);

				saveThumbState(-1);
			}
		}else{
			view.setThumbs(rating);

			BackendHandler.getInstance().addRatingVote(pub, rating);
			if(rating > 0){
				pub.setPositiveRating(pub.getPositiveRating() + 1);
			}
			else{
				pub.setNegativeRating(pub.getNegativeRating() + 1);
			}
			saveThumbState(rating);
		}
	}

	@Override
    public void saveFavoriteState(){
        SharedPreferences.Editor editor = view.getSharedPreferences(pub.getID(), 0).edit();
        editor.putBoolean("star", !(view.getSharedPreferences(pub.getID(), 0).getBoolean("star", true)));
        editor.commit();
    }

    /**
     * Saves the state of the thumb because a user is only allowed to vote 1 time.
     * @param thumb
     */
	private void saveThumbState(int thumb){
		SharedPreferences.Editor editor = view.getSharedPreferences(pub.getID(), 0).edit();
		editor.putInt("thumb", thumb);
		editor.commit();
	}

    @Override
	public void getQueueTime(){
		view.updateQueueIndicator(pub.getQueueTime());
	}

    @Override
	public void getText(){
		view.updateText(pub.getName(), pub.getDescription(), StringConverter.convertOpeningHours(pub.getTodaysOpeningHour()) + " - " +
				(StringConverter.convertOpeningHours(pub.getTodaysClosingHour())), ""+pub.getAgeRestriction() + " År", ""+pub.getEntranceFee()
				+ " :-");
	}

    @Override
	public void getThumbs(){
		view.setThumbs(view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0));
	}

    @Override
    public void getFavoriteStar(){
        view.updateStar(view.getSharedPreferences(pub.getID(), 0).getBoolean("star", true));
    }

    @Override
	public void getVotes() throws NoBackendAccessException, NotFoundInBackendException{
		view.updateVotes(""+ pub.getPositiveRating(), ""+ pub.getNegativeRating());
	}

	@Override
    public void updateInfo() throws NoBackendAccessException, NotFoundInBackendException, BackendNotInitializedException{
        BackendHandler.getInstance().updatePubLocally(pub);
    }
}
