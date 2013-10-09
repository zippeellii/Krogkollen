package se.chalmers.KrogkollenAdmin.buttons;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import se.chalmers.KrogkollenAdmin.R;
import se.chalmers.KrogkollenAdmin.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The presenter class for the ButtonsActivity. It holds all the data and logic for the view.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class ButtonsPresenter {

    private int queueTime;
    private ParseObject object;
    private ButtonsActivity view;
    private Timer inputDisabledTimer;
    public static final int DISABLE_TIME = 20000;
    private boolean buttonOnCooldown;
    private Toast toast;

    /**
     * Constructor. Gets called when the ButtonsActivity is started, so they know each other.
     *
     * @param butt The activity that should know about this presenter.
     */
    public ButtonsPresenter(ButtonsActivity butt) {
        view = butt;

        runTimer();
    }

    /**
     * Runs a timer that handles disabling of the buttons. Makes sure that the user doesn't spam the server by disabling more than one click every 20 second.
     */
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
     * This method is called by the timer every time it reaches the DISABLE_TIME.
     */
    private void timerFinished() {
        //This method runs in the same thread as the timer.
        view.runOnUiThread(Timer_Tick);
    }

    /**
     * This method runs in the same thread as the UI.
     * It activates the buttons and updates the GUI.
     */
    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            //Changes the UI to let the user know that input isn't allowed for a while.
            //This method runs in the same thread as the GUI.
            buttonOnCooldown = false;
            view.updateGUI();
        }
    };

    /**
     * Calls some methods when a button is clicked.
     *
     * @param newQueueTime An int corresponding to the button clicked.
     */
    public void buttonClicked(int newQueueTime) {
        if (buttonOnCooldown) {
            // Makes so that toasts doesn't stack.
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(view, view.getResources().getString(R.string.wait_part_one) + " " + ButtonsPresenter.DISABLE_TIME / 1000 + " " + view.getResources().getString(R.string.wait_part_two), Toast.LENGTH_SHORT);
            toast.setDuration(1);
            toast.show();
        } else {
            buttonOnCooldown = true;        // So that no input is accepted in a while
            new ServerUpdateTask().execute(newQueueTime);
        }
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

    /**
     * Setter for the variable that decides if input is allowed or not.
     *
     * @param buttonOnCooldown Set to true if you want to disable input.
     */
    public void setButtonOnCooldown(boolean buttonOnCooldown) {
        this.buttonOnCooldown = buttonOnCooldown;
    }

    /**
     * Getter for the variable that decides if input is allowed or not.
     *
     * @return True if the input is disabled.
     */
    public boolean getButtonOnCooldown() {
        return buttonOnCooldown;
    }

    public void logOut() {
        ParseUser.logOut();
        Intent intent = new Intent(view, MainActivity.class);
        view.startActivity(intent);
    }


    /**
     * A task to be be run on another thread, making sure that it shows a loading indicator when the task is executing.
     */
    private class ServerUpdateTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            // This is what we want to do before the possibly time-consuming task is started.
            view.showProgressDialog();
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            // This is what we want to do, that might take some time.
            setServerQueueTime(integers[0]);
            setLocalQueueTime();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // This is what we want to do when the task is done.
            view.updateGUI();
            view.hideProgressDialog();
        }
    }
}
