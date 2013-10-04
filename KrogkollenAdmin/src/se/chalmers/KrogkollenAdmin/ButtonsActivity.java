package se.chalmers.KrogkollenAdmin;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Primary activity for the admin application.
 * Shows three buttons which you can click to send up different queuetimes to the server.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class ButtonsActivity extends Activity {
    private Button greenButton;
    private Button yellowButton;
    private Button redButton;
    private int queueTime;
    private Timer inputDisabledTimer;
    private final int DISABLE_TIME = 60000;
    private ParseObject object;
    private ProgressBar circle;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        getActionBar().setDisplayShowHomeEnabled(false);
        circle = (ProgressBar) findViewById(R.id.marker_progress);
        circle.setVisibility(View.INVISIBLE);
        setupParseObject();
        addListenersOnButtons();
        setLocalQueueTime();
        activateButtons();
        updateGUI();

        inputDisabledTimer = new Timer();
        inputDisabledTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerFinished();
            }

        }, 0, DISABLE_TIME);
    }

    /**
     * Disables the buttons, making them do nothing on a click.
     */
    private void deactivateButtons() {
        redButton.setEnabled(false);
        yellowButton.setEnabled(false);
        greenButton.setEnabled(false);
    }

    /**
     * This method is called by the timer everytime it reaches the DISABLE_TIME.
     */
    private void timerFinished() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.

        this.runOnUiThread(Timer_Tick);
    }

    /**
     * Activates the buttons, making them work on clicks again.
     */
    private void activateButtons() {
        redButton.setEnabled(true);
        yellowButton.setEnabled(true);
        greenButton.setEnabled(true);
    }

    /**
     * This method runs in the same thread as the UI.
     * It activates the buttons and updates the GUI.
     */
    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            activateButtons();
            updateGUI();

            //Change the UI to give a hint that no input will be taken for the amount of time set.

            //This method runs in the same thread as the UI.

            //Do something to the UI thread here

        }
    };

    /**
     * Adds listeners to all the buttons as well as sets up the XML-connection to the buttons.
     */
    private void addListenersOnButtons() {

        greenButton = (Button) findViewById(R.id.green_button);
        yellowButton = (Button) findViewById(R.id.yellow_button);
        redButton = (Button) findViewById(R.id.red_button);

        greenButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                buttonClicked(1);
                return true;
            }
        });

        yellowButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                buttonClicked(2);
                return true;
            }
        });

        redButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                buttonClicked(3);
                return true;
            }
        });
    }

    /**
     * Updates the GUI. Changes which button is selected and sets the text on the top of the screen.
     */
    private void updateGUI() {
        String color;

        switch (queueTime) {
            case 1: color = "Nuvarande: <font color='#70c656'>Grön</font>";
                setTitle(Html.fromHtml(color));
                redButton.setSelected(false);
                yellowButton.setSelected(false);
                greenButton.setSelected(true);
                break;
            case 2: color = "Nuvarande: <font color='#f3ae1b'>Gul</font>";
                setTitle(Html.fromHtml(color));
                redButton.setSelected(false);
                yellowButton.setSelected(true);
                greenButton.setSelected(false);
                break;
            case 3: color = "Nuvarande: <font color='#ef4444'>Röd</font>";
                setTitle(Html.fromHtml(color));
                redButton.setSelected(true);
                yellowButton.setSelected(false);
                greenButton.setSelected(false);
                break;
            default: setTitle("Nuvarande: Ingen information");
        }

        if (!redButton.isEnabled()) {
            setTitle("Vänta en minut mellan klicken...");
        }
    }

    /**
     * Sets up the connection to Parse.com.
     * Gets the object that corresponds to the logged in user.
     */
    private void setupParseObject() {
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
    private void setLocalQueueTime() {
        queueTime = object.getInt("queueTime");
    }

    /**
     * Takes the local queueTime and sends it to the sever.
     * @param newQueueTime The queueTime to be sent to the server.
     */
    private void setServerQueueTime(int newQueueTime) {
        try {
            object.put("queueTime", newQueueTime);
            object.save();
        } catch (ParseException pe) {
            return;
        }
    }

    /**
     * Calls some methods when a button is clicked.
     *
     * @param newQueueTime An int corresponding to the button clicked.
     */
    private void buttonClicked(int newQueueTime) {
        circle.setVisibility(View.VISIBLE);
        setServerQueueTime(newQueueTime);
        setLocalQueueTime();
        deactivateButtons();
        updateGUI();
    }
}
