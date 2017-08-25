package com.crogers.caltrainschedule;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class RoutesScreen extends AppCompatActivity {
    ScheduleDataStore dataStore;
    public RoutesScreen(){
        dataStore = new ScheduleDataStore();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_screen);

        setUpArrivingSpinner();
        setUpDepartingSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_routes_screen, menu);
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

    private void setUpArrivingSpinner(){
        final Spinner as = (Spinner) findViewById(R.id.arriveSpinner);
        as.setAdapter(dataStore.getArrivingAdapter(this));
        as.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int myPosition, long myID) {
                updateResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Dont do nothin
            }

        });
    }
    private void setUpDepartingSpinner(){
        final Spinner ds = (Spinner) findViewById(R.id.departSpinner);
        ds.setAdapter(dataStore.getDepartingAdapter(this));
        ds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int myPosition, long myID) {
                updateResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Dont do nothin
            }

        });
    }

    private void updateResult(){
        Spinner as = (Spinner) findViewById(R.id.arriveSpinner);
        Spinner ds = (Spinner) findViewById(R.id.departSpinner);
        String arrival = as.getSelectedItem().toString();
        String departure = ds.getSelectedItem().toString();
        String result = dataStore.getTimeDeltas(arrival, departure);
        TextView resultsText = (TextView) findViewById(R.id.resultsText);
        resultsText.setText(result);

        TextView arrivalText = (TextView) findViewById(R.id.arriveText);
        arrivalText.setText(arrival);

        TextView departureText = (TextView) findViewById(R.id.departText);
        departureText.setText(departure);

    }
}
