package com.hiqes.sensorframeworkdemo;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = MainActivity.class.getName();
    private SensorManager mSensorManager;
    private List<Sensor> mSensors;
    private SensorAdapter mAdapter;
    private ListView lstSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        if (mSensors != null) {
            Log.i(TAG, "Dump of all sensors on device");
            for(Sensor curSensor : mSensors) {
                Log.i(TAG, "Name: " + curSensor.getName());
                Log.i(TAG, "Vendor: " + curSensor.getVendor());
                Log.i(TAG, "Version: " + curSensor.getVersion());
                Log.i(TAG, "Type: " + curSensor.getType());
            }
        }

        lstSensors = (ListView) findViewById(R.id.lstsensor);
        mAdapter = new SensorAdapter(this, mSensors);
        lstSensors.setAdapter(mAdapter);
        lstSensors.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, SensorDataActivity.class);
        Sensor s = mSensors.get(position);
        intent.putExtra("Type", s.getType());
        startActivity(intent);
    }
}
