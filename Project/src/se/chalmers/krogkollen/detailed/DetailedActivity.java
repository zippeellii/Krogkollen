package se.chalmers.krogkollen.detailed;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;
import se.chalmers.krogkollen.pub.Pub;
import se.chalmers.krogkollen.pub.PubUtilities;

/**
 * Created with IntelliJ IDEA.
 * User: perthoresson
 * Date: 2013-09-25
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
public class DetailedActivity extends Activity {

    IPub pub;

    Pub testPub = new Pub("TestPub", "Så kallad beskrivning... Viktigt. byter rad också. Vackert. Per och Filip levererar #öl", "17-03", 18, 30, 80.21, 90.23,100, 23);

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

    public void setText(){
        TextView pubTextView = (TextView) findViewById(R.id.pub_name);
        pubTextView.setText(testPub.getName());
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        descriptionTextView.setText(testPub.getDescription());
    }
}