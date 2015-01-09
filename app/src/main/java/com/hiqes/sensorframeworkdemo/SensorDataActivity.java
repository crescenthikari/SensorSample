package com.hiqes.sensorframeworkdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class SensorDataActivity extends ActionBarActivity implements SensorEventListener {
    private static final String TAG = SensorDataActivity.class.getName();

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView mTxtSensorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sensordata);
        mTxtSensorData = (TextView)findViewById(R.id.txtSensorData);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        int sensorType = getIntent().getIntExtra("Type", -1);
        if (sensorType == -1) {
            Log.e(TAG, "No type provided");
            finish();
        } else {
            mSensor = mSensorManager.getDefaultSensor(sensorType);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sensor Name: ");
        sb.append(event.sensor.getName());
        sb.append("\n");
        sb.append("Time: ");
        sb.append(event.timestamp);
        sb.append("\n");
        sb.append("Accuracy: ");
        sb.append(event.accuracy);
        sb.append("\n");

        for(float v : event.values){
            sb.append(v);
            sb.append("\n");
        }

        mTxtSensorData.setText(sb.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
