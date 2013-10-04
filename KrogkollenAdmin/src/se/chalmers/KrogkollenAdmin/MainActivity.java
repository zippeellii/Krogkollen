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

    private Button loginButton;
    private AutoCompleteTextView userNameField;
    private EditText passwordField;
    private ProgressBar circle;
    private String[] pubUsers;

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
        circle = (ProgressBar) findViewById(R.id.marker_progress);
        circle.setVisibility(View.VISIBLE);

        initializePubUsers();

        passwordField = (EditText) findViewById(R.id.txtPassword);
        loginButton = (Button) findViewById(R.id.login_button);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        addListeners();
    }

    private final void initializePubUsers() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {
                    usersWereRetrievedSuccessfully(users);
                } else {
                    userRetrievalFailed();
                }
            }
        });
    }

    private void userRetrievalFailed() {
        // Well shit
    }

    private void usersWereRetrievedSuccessfully(List<ParseUser> users) {
        pubUsers = new String[users.size()];
        for (int i = 0; i < pubUsers.length; i++) {
            pubUsers[i] = users.get(i).getString("username");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, pubUsers);
        userNameField = (AutoCompleteTextView)
                findViewById(R.id.txtPubName);
        userNameField.setAdapter(adapter);
        userNameField.requestFocus();
    }

    private void loginButtonClicked() {
        boolean loginSuccess = false;

        try {
            ParseUser.logIn(userNameField.getText().toString(), passwordField.getText().toString());
            loginSuccess = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (loginSuccess) {
            Intent intent = new Intent(this, ButtonsActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(MainActivity.this, R.string.wrong_password_message,
                    Toast.LENGTH_SHORT);
            toast.setDuration(2);
            toast.show();
        }
    }

    private void addListeners() {
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
