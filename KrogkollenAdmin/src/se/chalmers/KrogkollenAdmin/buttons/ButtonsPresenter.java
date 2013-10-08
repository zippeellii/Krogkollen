package se.chalmers.KrogkollenAdmin.buttons;

import android.os.AsyncTask;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class ButtonsPresenter {

    private int queueTime;
    private ParseObject object;
    private ButtonsActivity activity;
    private Timer inputDisabledTimer;
    public final int DISABLE_TIME = 20000;
    private boolean buttonOnCooldown;

    public ButtonsPresenter(ButtonsActivity butt) {
        activity = butt;

        runTimer();
    }

    public void runTimer() {
        inputDisabledTimer = new Timer();
        inputDisabledTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerFinished();
            }

        }, 0, DISABLE_TIME);
    }

    /**
     * This method is called by the timer everytime it reaches the DISABLE_TIME.
     */
    private void timerFinished() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.

        activity.runOnUiThread(Timer_Tick);
    }

    /**
     * This method runs in the same thread as the UI.
     * It activates the buttons and updates the GUI.
     */
    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            buttonOnCooldown = false;
            activity.updateGUI();

            //Change the UI to give a hint that no input will be taken for the amount of time set.

            //This method runs in the same thread as the UI.

            //Do something to the UI thread here

        }
    };

    /**
     * Calls some methods when a button is clicked.
     *
     * @param newQueueTime An int corresponding to the button clicked.
     */
    public void buttonClicked(int newQueueTime) {
        new ServerUpdateTask().execute(newQueueTime);
    }

    /**
     * Sets up the connection to Parse.com.
     * Gets the object that corresponds to the logged in user.
     */
    public void setupParseObject() {
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Pub");
            query.whereEqualTo("owner", ParseUser.getCurrentUser());
            object = query.getFirst();
        } catch (ParseException pe) {
            return;
        }
    }

    /**
     * Gets the queueTime from the server and syncs locally.
     */
    synchronized void setLocalQueueTime() {
        queueTime = object.getInt("queueTime");
    }

    /**
     * Takes the local queueTime and sends it to the sever.
     * @param newQueueTime The queueTime to be sent to the server.
     */
    synchronized void setServerQueueTime(int newQueueTime) {
        try {
            object.put("queueTime", newQueueTime);
            object.save();
        } catch (ParseException pe) {
            return;
        }
    }

    /**
     * Returns the queue time.
     *
     * @return the queue time
     */
    public int getQueueTime() {
        return queueTime;
    }

    public void setButtonOnCooldown(boolean buttonOnCooldown) {
        this.buttonOnCooldown = buttonOnCooldown;
    }

    public boolean getButtonOnCooldown() {
        return buttonOnCooldown;
    }


    private class ServerUpdateTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute()
        {
            activity.showProgressDialog();
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            setServerQueueTime(integers[0]);
            setLocalQueueTime();
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            activity.updateGUI();
            activity.hideProgressDialog();
        }
    }
}
