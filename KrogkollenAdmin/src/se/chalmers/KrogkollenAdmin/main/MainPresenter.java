package se.chalmers.KrogkollenAdmin.main;

import android.content.Intent;
import android.widget.Toast;
import com.parse.*;
import se.chalmers.KrogkollenAdmin.buttons.ButtonsActivity;

import java.util.List;

/**
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class MainPresenter {

    private String[] pubUsers;
    private MainActivity activity;

    public MainPresenter(MainActivity main) {
        activity = main;
    }

    public String[] getPubUsers() {
        return pubUsers;
    }

    /**
     * Sets up the connection with the Parse.com server
     * Also gives notice that this app has connected to the server.
     */
    public void setupParseConnection() {
        Parse.initialize(activity, "WgLQnilANHpjM3xITq0nM0eW8dByIgDDmxJzf6se", "9ZK7yjE1NiD244ymDHb8ZpbbWNNv3RuQq7ceEvJc");
        ParseAnalytics.trackAppOpened(activity.getIntent());
    }

    /**
     * Tries to use the information in the login- and passwordfields to log in. Calls the logIn-method from Parse.com
     * If it succeeds it sends an intent to start ButtonsActivity, otherwise it gives a toast with an errormessage.
     */
    public void tryLogin(String username, String password) {
        boolean loginSuccess = false;

        try {
            ParseUser.logIn(username, password);
            loginSuccess = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (loginSuccess) {
            Intent intent = new Intent(activity, ButtonsActivity.class);
            activity.startActivity(intent);
        } else {
            Toast toast = Toast.makeText(activity, se.chalmers.KrogkollenAdmin.R.string.wrong_password_message,
                    Toast.LENGTH_SHORT);
            toast.setDuration(2);
            toast.show();
        }
    }

    /**
     * Grabs all the users from Parse.com and puts them in an array.
     */
    public void initializePubUsers() {
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

    /**
     * If the connection to Parse.com failed this method is called.
     */
    private void userRetrievalFailed() {
        // Can't access database. Give some message here
    }

    /**
     * If the connection to Parse.com was successful this method is called.
     * It puts the users in the array pubUsers.
     *
     * @param users The users to be put into the array.
     */
    private void usersWereRetrievedSuccessfully(List<ParseUser> users) {
        pubUsers = new String[users.size()];
        for (int i = 0; i < pubUsers.length; i++) {
            pubUsers[i] = users.get(i).getString("username");
        }

        // Gets called here since this is a place where we are sure that the users have been loaded.
        // If we would've called this somewhere else they might not have finished loading and thus we would get no values.
        activity.setupAutocompletion();
    }
}
