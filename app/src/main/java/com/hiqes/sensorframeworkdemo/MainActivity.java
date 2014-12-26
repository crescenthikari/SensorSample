package com.hiqes.sensorframeworkdemo;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private SensorManager mSensorManager;
    private SensorAdapter mAdapter;
    private ListView lstSensors;
    private List<Sensor> arrSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        arrSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        lstSensors = (ListView) findViewById(R.id.lstsensor);
        mAdapter = new SensorAdapter(this, arrSensors);
        lstSensors.setAdapter(mAdapter);
        lstSensors.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,SensorData.class);
        Sensor s = arrSensors.get(position);
        intent.putExtra("Type",s.getType());
        startActivity(intent);
    }
}
