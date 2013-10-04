package se.chalmers.KrogkollenAdmin.buttons;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import se.chalmers.KrogkollenAdmin.R;

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
//    private ProgressBar circle;       Not yet used
    private ButtonsPresenter presenter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        getActionBar().setDisplayShowHomeEnabled(false);
        presenter = new ButtonsPresenter(this);


        presenter.setupParseObject();
        presenter.setLocalQueueTime();
        setupUiElements();
        addListenersOnButtons();
        activateButtons();
        updateGUI();
    }

    /**
     * Sets up all the buttons and the loading indicator which is a circle.
     */
    private void setupUiElements() {
        greenButton = (Button) findViewById(R.id.green_button);
        yellowButton = (Button) findViewById(R.id.yellow_button);
        redButton = (Button) findViewById(R.id.red_button);

        // Sets up the loading indicator        Not yet used
//        circle = (ProgressBar) findViewById(R.id.marker_progress);
//        circle.setVisibility(View.INVISIBLE);
    }

    /**
     * Disables the buttons, making them do nothing on a click.
     */
    public void deactivateButtons() {
        redButton.setEnabled(false);
        yellowButton.setEnabled(false);
        greenButton.setEnabled(false);
    }

    /**
     * Activates the buttons, making them work on clicks again.
     */
    public void activateButtons() {
        redButton.setEnabled(true);
        yellowButton.setEnabled(true);
        greenButton.setEnabled(true);
    }

    /**
     * Adds listeners to all the buttons as well as sets up the XML-connection to the buttons.
     */
    private void addListenersOnButtons() {
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
     * Sets the buttons visibility and then tells the presenter which button got clicked.
     * @param i the button clicked.
     */
    private void buttonClicked(int i) {
//        circle.setVisibility(View.VISIBLE);       Not yet used
        presenter.buttonClicked(i);
    }

    /**
     * Updates the GUI. Changes which button is selected and sets the text on the top of the screen.
     */
    public void updateGUI() {
        String color;

        switch (presenter.getQueueTime()) {
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
}
