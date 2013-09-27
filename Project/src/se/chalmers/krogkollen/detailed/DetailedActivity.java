package se.chalmers.krogkollen.detailed;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.pub.PubUtilities;

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
 *
 */

// TODO write javadoc
public class DetailedActivity extends Activity {

    IPub pub;

    Pub testPub = new Pub();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        if (getIntent()!=null){
            int pubID = getIntent().getIntExtra("pubID", 0);
            pub = PubUtilities.getInstance().getPub(pubID);
        }

        setText();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailed, menu);

        return true;
    }

    // TODO: javadoc
    public void setText(){
        TextView pubTextView = (TextView) findViewById(R.id.pub_name);
        pubTextView.setText(testPub.getName());
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        descriptionTextView.setText(testPub.getDescription());
    }
}