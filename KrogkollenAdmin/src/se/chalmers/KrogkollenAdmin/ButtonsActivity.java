package se.chalmers.KrogkollenAdmin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

/**
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class ButtonsActivity extends Activity {
    private Button greenButton;
    private Button yellowButton;
    private Button redButton;
    private String pubId;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        pubId = getIntent().getStringExtra(MainActivity.PUB_ID);
        getActionBar().setDisplayShowHomeEnabled(false);
        addListenersOnButtons();
    }

    public void addListenersOnButtons() {

        greenButton = (Button) findViewById(R.id.green_button);
        yellowButton = (Button) findViewById(R.id.yellow_button);
        redButton = (Button) findViewById(R.id.red_button);

        greenButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                informationUpdated(1);
                return true;
            }
        });

        yellowButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                informationUpdated(2);
                return true;
            }
        });

        redButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                informationUpdated(3);
                return true;
            }
        });
    }

    /**
     * This needs to be changed to be strings in values/strings
     *
     * @param i
     */
    public void informationUpdated(int i) {
        String color;
        switch (i) {
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

    }
}
