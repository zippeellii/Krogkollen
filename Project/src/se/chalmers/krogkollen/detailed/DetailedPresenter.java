package se.chalmers.krogkollen.detailed;

import android.content.SharedPreferences;
import android.view.View;
import se.chalmers.krogkollen.IView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.backend.Backend;
import se.chalmers.krogkollen.backend.NoBackendAccessException;
import se.chalmers.krogkollen.backend.NotFoundInBackendException;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.PubUtilities;

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

    /**
     * Sets the pub and updates it locally
     * @param pubID The pubs ID
     * @throws NoBackendAccessException
     * @throws NotFoundInBackendException
     */
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

    /**
     * Saves the state of the favorite locally
     */
    public void saveFavoriteState(){
        SharedPreferences.Editor editor = view.getSharedPreferences(pub.getID(), 0).edit();
        editor.putBoolean("star", !(view.getSharedPreferences(pub.getID(), 0).getBoolean("star", true)));
        editor.commit();
    }

    /**
     *
     * Saves the state of the thumb because a user is only allowed to vote 1 time.
     * @param thumb
     */
	public void saveThumbState(int thumb){
		SharedPreferences.Editor editor = view.getSharedPreferences(pub.getID(), 0).edit();
		editor.putInt("thumb", thumb);
		editor.commit();
	}

    /**
     * Converts the hour string
     * @param hour
     * @return
     */
	public String convertOpeningHours(int hour){
		if(hour / 10 ==0){
			return "0"+hour;
		}
		return ""+hour;
	}

    /**
     * Sends the queue time to the view
     */
	public void getQueueTime(){
		view.updateQueueIndicator(pub.getQueueTime());
	}

    /**
     * Sends the name, description, opening hours, age restriction and entrance fee to the view
     */
	public void getText(){
		view.updateText(pub.getName(), pub.getDescription(), convertOpeningHours(pub.getTodaysOpeningHour()) + " - " +
				(convertOpeningHours(pub.getTodaysClosingHour())), ""+pub.getAgeRestriction() + " År", ""+pub.getEntranceFee()
				+ " :-");
	}

    /**
     * Tells the view whether the thumb down or the thumb up is clicked.
     */
	public void getThumbs(){
		view.setThumbs(view.getSharedPreferences(pub.getID(), 0).getInt("thumb", 0));
	}

    /**
     * Tells the view weather the star is clicked or not.
     */
    public void getFavoriteStar(){
        view.updateStar(view.getSharedPreferences(pub.getID(), 0).getBoolean("star", true));
    }

    /**
     * Sends the current rating to the view.
     * @throws NoBackendAccessException
     * @throws NotFoundInBackendException
     */
	public void getVotes() throws NoBackendAccessException, NotFoundInBackendException{
		view.updateVotes(""+ pub.getPositiveRating(), ""+ pub.getNegativeRating());
	}

    /**
     * Updates the info of a pub
     * @throws NoBackendAccessException
     * @throws NotFoundInBackendException
     */
    public void updateInfo() throws NoBackendAccessException, NotFoundInBackendException{
        Backend.getInstance().updatePubLocally(pub);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.thumbsDownLayout){
            try {
                ratingChanged(-1);
                getVotes();
            } catch (NotFoundInBackendException e) {
                System.out.println("error");
            } catch (NoBackendAccessException e) {
                System.out.println("error");
            }
        }
        else if(view.getId() == R.id.thumbsUpLayout){
            try {
                ratingChanged(1);
                getVotes();

            } catch (NotFoundInBackendException e) {
                System.out.println("error");
            } catch (NoBackendAccessException e) {
                System.out.println("error");
            }
        }
    }
}
