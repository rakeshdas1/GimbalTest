package com.rakeshdas.gimbaltest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gimbal.android.Gimbal;
import com.gimbal.android.PlaceEventListener;
import com.gimbal.android.Visit;


public class MainActivity extends ActionBarActivity {
    private PlaceEventListener placeEventListener;
    private String TAG = "beacon";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Gimbal.setApiKey(this.getApplication(), "c035477e-47e2-419c-93de-5507b7c8d029");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeEventListener = new PlaceEventListener() {
            @Override
            public void onVisitStart(Visit visit) {
                super.onVisitStart(visit);
                Log.i(TAG, "I see a beacon!");


            }

            @Override
            public void onVisitEnd(Visit visit) {
                super.onVisitEnd(visit);
            }
        };
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
