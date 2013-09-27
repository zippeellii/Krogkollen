package se.chalmers.krogkollen.detailed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.pub.PubUtilities;

/**
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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Created with IntelliJ IDEA.
 * User: perthoresson
 * Date: 2013-09-25
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
public class DetailedActivity extends Activity {

    IPub pub;

    Pub testPub = new Pub();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        if (getIntent()!=null){
            int pubID = getIntent().getIntExtra("pubID", 0);
            pub = PubUtilities.getInstance().getPub(pubID);
        }

        setText();

    }

    public void setText(){
        TextView pubTextView = (TextView) findViewById(R.id.pub_name);
        pubTextView.setText(testPub.getName());
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        descriptionTextView.setText(testPub.getDescription());
    }
}