package se.chalmers.KrogkollenAdmin.buttons;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.*;
import android.widget.Button;
import se.chalmers.KrogkollenAdmin.R;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    private ProgressDialog progressDialog;
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
        presenter.startTimers();
        presenter.setupParseObject();
        presenter.setLocalQueueTime();
        setupUiElements();
        addListenersOnButtons();
        updateGUI();
        for (int i = 0; i < 10; i++) {
            System.out.println("OMG FU");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.logout_item) {
            logOut();

            return true;
        } else if (menuItem.getItemId() == R.id.notifications_item) {
            menuItem.setChecked(!menuItem.isChecked());
            toggleNotifications();
        }
        return false;
    }

    private void toggleNotifications() {
        presenter.toggleNotifications();
    }

    private void logOut() {
        presenter.logOut();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buttons, menu);
        return true;
    }

    /**
     * Sets up all the buttons and the loading indicator which is a circle.
     */
    private void setupUiElements() {
        greenButton = (Button) findViewById(R.id.green_button);
        yellowButton = (Button) findViewById(R.id.yellow_button);
        redButton = (Button) findViewById(R.id.red_button);
    }

    /**
     * Adds listeners to all the buttons as well as sets up the XML-connection to the buttons.
     */
    private void addListenersOnButtons() {
        // We used on touch listeners instead of on click to avoid a bug with selection of buttons.

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
     *
     * @param i the button clicked.
     */
    private void buttonClicked(int i) {
        presenter.buttonClicked(i);
    }

    /**
     * Shows that the application is loading something from or up to the server.
     */
    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.updating), false, false);
    }

    /**
     * Removes the loading-indicator.
     */
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    /**
     * Updates the GUI. Changes which button is selected and sets the text on the top of the screen.
     */
    public void updateGUI() {
        String msg;
/*
        if (presenter.getButtonOnCooldown()) {
            // Sets the first part of the string to tell the user to wait.
            msg = getResources().getString(R.string.wait_part_one) + " " + (presenter.DISABLE_TIME/1000) + " " + getResources().getString(R.string.wait_part_two);
        } else {
            // Sets the first part of the string to tell the user it is ready.
            msg = getResources().getString(R.string.ready);
        }
*/
        redButton.setSelected(false);
        yellowButton.setSelected(false);
        greenButton.setSelected(false);

        // These cases adds the current button active to the string. If there is one.
        // They also set that button to be selected.
        switch (presenter.getQueueTime()) {
            case 1:
                msg = getResources().getString(R.string.queue_now) +
                        " <font color='#70c656'>" + getResources().getString(R.string.green) + "</font>";
                setTitle(Html.fromHtml(msg));
                greenButton.setSelected(true);
                break;
            case 2:
                msg = getResources().getString(R.string.queue_now) +
                        " <font color='#f3ae1b'>" + getResources().getString(R.string.yellow) + "</font>";
                setTitle(Html.fromHtml(msg));
                yellowButton.setSelected(true);
                break;
            case 3:
                msg = getResources().getString(R.string.queue_now) +
                        " <font color='#ef4444'>" + getResources().getString(R.string.red) + "</font>";
                setTitle(Html.fromHtml(msg));
                redButton.setSelected(true);
                break;
            default:
                msg = getResources().getString(R.string.buttons_activity);
                setTitle(msg);
                break;
        }
    }
}
