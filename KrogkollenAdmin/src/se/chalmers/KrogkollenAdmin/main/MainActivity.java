package se.chalmers.KrogkollenAdmin.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
 * First activity for the admin application.
 * Shows a standard login screen for the user which can take you to the ButtonsActivity.
 *
 * @author Albin Garpetun
 *         Created 2013-09-22
 */
public class MainActivity extends Activity {

    private Button loginButton;
    private AutoCompleteTextView userNameField;
    private EditText passwordField;
    private ProgressDialog progressDialog;
    private MainPresenter presenter;

    /**
     * Called when the activity is first created.
     * <p/>
     * Initiates all the objects in the class and calls methods to setup server-connection.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(se.chalmers.KrogkollenAdmin.R.layout.activity_main);
        presenter = new MainPresenter(this);

        presenter.setupParseConnection();
        presenter.initializePubUsers();

        setupUiElements();
        addListeners();

        presenter.checkIfLoggedIn();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    /**
     * Sets up the progress indicator which is a circle.
     * Sets up the fields and the login button.
     */
    private void setupUiElements() {
        // Sets up the username with auto completion
        userNameField = (AutoCompleteTextView)
                findViewById(se.chalmers.KrogkollenAdmin.R.id.txtPubName);
        userNameField.requestFocus();

        passwordField = (EditText) findViewById(se.chalmers.KrogkollenAdmin.R.id.txtPassword);
        loginButton = (Button) findViewById(se.chalmers.KrogkollenAdmin.R.id.login_button);

        getActionBar().hide();
    }

    /**
     * Associates the userNameField with a list of strings so that auto completion is available.
     */
    public void setupAutocompletion() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, presenter.getPubUsers());
        userNameField.setAdapter(adapter);

    }

    /**
     * Shows that the application is loading something from or up to the server.
     */
    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.logging_in), false, false);
    }

    /**
     * Hides the loading-indicator.
     */
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    // Tries to use the information in the login- and passwordfields to log in. Calls the logIn-method from Parse.com
    // If it succeeds it sends an intent to start ButtonsActivity, otherwise it gives a toast with an errormessage.
    private void loginButtonClicked() {
        presenter.tryLogin(userNameField.getText().toString(), passwordField.getText().toString());
    }

    // Adds listener to the loginButton.
    // Also adds listeners to the other fields so that they proceed naturally after each other.(Hop to the next field on enter-click)
    private void addListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButtonClicked();
            }
        });

        // This listener makes sure to hide the on-screen keyboard if they click 'next' in the password-field.
        passwordField.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
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

    /**
     * Returns the shared preferences of this application.
     * From this you can tell if you are already logged in or not.
     *
     * @return The shared preferences.
     */
    public SharedPreferences getPreferences() {
        return this.getPreferences(Context.MODE_PRIVATE);
    }
}
