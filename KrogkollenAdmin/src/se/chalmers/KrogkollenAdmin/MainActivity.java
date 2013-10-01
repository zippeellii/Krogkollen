package se.chalmers.KrogkollenAdmin;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.parse.*;

import java.util.ArrayList;
import java.util.List;

/**
 * First activity for the admin application. Shows a standard login screen for the user.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class MainActivity extends Activity {

    public final static String PUB_ID = "se.chalmers.KrogkollenAdmin.PUB_ID";
    private List<ParseObject> pubParseObject;
    private List<String> pubNames = new ArrayList<String>();
    private List<String> pubObjectIds = new ArrayList<String>();
    private Button loginButton;
    private EditText pubNameField;
    private EditText passwordField;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Initializing the connection with the server
        Parse.initialize(this, "WgLQnilANHpjM3xITq0nM0eW8dByIgDDmxJzf6se", "9ZK7yjE1NiD244ymDHb8ZpbbWNNv3RuQq7ceEvJc");
        ParseAnalytics.trackAppOpened(getIntent());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        addPubsToList();
        addListeners();
    }

    private void addPubsToList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pub");

        try {
            pubParseObject = query.find();
            for (ParseObject object : pubParseObject) {
                pubObjectIds.add(object.getString("objectId"));
                pubNames.add(object.getString("name"));
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    private void loginButtonClicked() {
        boolean pubFound = false;
        for (int i = 0; i < pubNames.size(); i++) {
            if (pubNameField.getText().toString().equals(pubNames.get(i))) {
                pubFound = true;
                break;
            }
        }

        // Temporary password-checking with password 'asdf' working for all pubs
        if (passwordField.getText().toString().equals("asdf") && pubFound) {

        Intent intent = new Intent(this, ButtonsActivity.class);

            for (int i = 0; i < pubNames.size(); i++) {
                if (pubNameField.getText().toString().equals(pubNames.get(i))) {
                    intent.putExtra(PUB_ID, pubObjectIds.get(i));
                    break;
                }
            }

            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(MainActivity.this, R.string.wrong_password_message,
                    Toast.LENGTH_SHORT);
            toast.setDuration(2);
            toast.show();
        }
    }

    private void addListeners() {
        passwordField = (EditText) findViewById(R.id.txtPassword);
        pubNameField = (EditText) findViewById(R.id.txtPubName);        //Not yet used
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButtonClicked();
            }
        });

        passwordField.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(passwordField.getWindowToken(), 0);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }
}
