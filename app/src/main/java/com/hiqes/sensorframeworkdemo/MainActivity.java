package com.hiqes.sensorframeworkdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView txtSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensor = (TextView) findViewById(R.id.txtSensor);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mSensor == null) {
            txtSensor.setText("Light Sensor is not available in the device");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSensor != null) {
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSensor != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(mSensor.getName());
        sb.append("\n");
        sb.append("Vendor: ");
        sb.append(mSensor.getVendor());
        sb.append("\n");
        sb.append("Version: ");
        sb.append(mSensor.getVersion());
        sb.append("\n");
        sb.append("Type: ");
        sb.append(mSensor.getType());
        sb.append("\n");
        sb.append("Maximum Range: ");
        sb.append(mSensor.getMaximumRange());
        sb.append("\n");
        sb.append("Timestamp: ");
        sb.append(event.timestamp);
        sb.append("\n");
        sb.append("Value: ");
        sb.append(event.values[0]);
        txtSensor.setText(sb.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
