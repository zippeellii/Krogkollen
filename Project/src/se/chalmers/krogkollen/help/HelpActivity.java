package se.chalmers.krogkollen.help;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.list.ListActivity;
import se.chalmers.krogkollen.map.MapActivity;
import se.chalmers.krogkollen.utils.Constants;

/**
 * Activity for the help screen.
 *
 * @author Johan Backman 
 * Date: 2013-10-02
 */
public class HelpActivity extends Activity {

    private String previousActivityKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        previousActivityKey = getIntent().getStringExtra(Constants.ACTIVITY_FROM);

        setContentView(R.layout.activity_help);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help, menu);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.transparent_spacer);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this,
                        previousActivityKey.equalsIgnoreCase(Constants.LIST_ACTIVITY_NAME) ?
                                ListActivity.class : MapActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}