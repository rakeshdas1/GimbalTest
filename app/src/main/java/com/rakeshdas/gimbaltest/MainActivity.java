package com.rakeshdas.gimbaltest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.gimbal.android.BeaconEventListener;
import com.gimbal.android.BeaconManager;
import com.gimbal.android.BeaconSighting;
import com.gimbal.android.Gimbal;
import com.gimbal.android.PlaceEventListener;
import com.gimbal.android.PlaceManager;
import com.gimbal.android.Visit;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {
    private TextView mainText;
    private BeaconEventListener beaconSightingListener;
    private BeaconManager beaconManager;
    private PlaceEventListener placeEventListener;
    private String TAG = "beacon";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainText = (TextView)findViewById(R.id.defaultTextView);
        Gimbal.setApiKey(this.getApplication(), "c035477e-47e2-419c-93de-5507b7c8d029");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeEventListener = new PlaceEventListener() {
            @Override
            public void onVisitStart(Visit visit) {
                super.onVisitStart(visit);
                Log.i(TAG, "I see a beacon via placeEventListener!");
                mainText.setText(R.string.see_beacon);
            }

            @Override
            public void onVisitEnd(Visit visit) {
                super.onVisitEnd(visit);
                Log.i(TAG, "I lost a beacon via placeEventListener!");
                mainText.setText(R.string.lost_beacon);
            }
        };
        PlaceManager.getInstance().addListener(placeEventListener);


        beaconSightingListener = new BeaconEventListener() {
            @Override
            public void onBeaconSighting(BeaconSighting beaconSighting) {
                super.onBeaconSighting(beaconSighting);
                Log.i(TAG, "I saw a beacon via beaconSightingListener: " + beaconSighting.toString());
            }
        };
        beaconManager = new BeaconManager();
        beaconManager.addListener(beaconSightingListener);

        PlaceManager.getInstance().startMonitoring();
        beaconManager.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
