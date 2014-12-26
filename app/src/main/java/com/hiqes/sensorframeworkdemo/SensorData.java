package com.hiqes.sensorframeworkdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;


public class SensorData extends ActionBarActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int sensorType;
    private TextView txtSensorData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensordata);
        txtSensorData = (TextView) findViewById(R.id.txtSensorData);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorType = getIntent().getIntExtra("Type",1);
        mSensor = mSensorManager.getDefaultSensor(sensorType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sensor Name: " + event.sensor.getName());
        sb.append("\n");
        sb.append("Time: " + event.timestamp);
        sb.append("\n");
        sb.append("Accuracy: " + event.accuracy);
        sb.append("\n");

        for(float v : event.values){
            sb.append(v);
            sb.append("\n");
        }
        txtSensorData.setText(sb.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
