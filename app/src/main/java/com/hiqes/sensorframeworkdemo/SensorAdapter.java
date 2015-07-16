package com.hiqes.sensorframeworkdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SensorAdapter extends BaseAdapter {

    private List<Sensor> arrSensors;
    private Context ctx;
    SensorAdapter(Context ctx,List<Sensor> arrSensors){
        this.arrSensors = arrSensors;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return arrSensors.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSensors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh;
        if(convertView == null){
            view = LayoutInflater.from(ctx).inflate(R.layout.row_list,parent,false);
            vh = new ViewHolder();
            vh.txtMinDelay =(TextView) view.findViewById(R.id.txtMinDelay);
            vh.txtMaxRange = (TextView)view.findViewById(R.id.txtMaxRange);
            vh.txtSensorName =(TextView) view.findViewById(R.id.txtSensorName);
            vh.txtVendor =(TextView) view.findViewById(R.id.txtVendor);
            vh.txtVersion = (TextView) view.findViewById(R.id.txtVersion);
            vh.txtSensorType = (TextView) view.findViewById(R.id.txtSensorType);
            vh.txtPower = (TextView) view.findViewById(R.id.txtPower);
            view.setTag(vh);
        }
        else {
            vh = (ViewHolder) view.getTag();
        }

        Sensor s = arrSensors.get(position);
        vh.txtMinDelay.setText("Min Delay:" + s.getMinDelay());
        vh.txtVendor.setText("Vendor: " + s.getVendor() );
        vh.txtVersion.setText("Version: " + s.getVersion());
        vh.txtSensorName.setText(s.getName());
        vh.txtMaxRange.setText("Max Range:" + s.getMaximumRange());
        vh.txtSensorType.setText("Type: "+getStringType(s.getType()) + " Type Code:" + s.getType());
        vh.txtPower.setText("Power: "+s.getPower()+" mA");
        return  view;
    }

    static class ViewHolder{
        TextView txtSensorName;
        TextView txtVendor;
        TextView txtVersion;
        TextView txtMaxRange;
        TextView txtMinDelay;
        TextView txtSensorType;
        TextView txtPower;
    }

    private String getStringType(int type){
        String sensorType;
        switch(type){
            case Sensor.TYPE_ACCELEROMETER:
                sensorType= "Accelerometer";
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sensorType ="Ambient Temperature";
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                sensorType ="Game Rotation Vector";
                break;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                sensorType ="Geomagnetic Rotation Vector";
                break;
            case Sensor.TYPE_GRAVITY:
                sensorType="Gravity";
                break;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                sensorType="Gyroscope Uncalibrated";
                break;
            case Sensor.TYPE_GYROSCOPE:
                sensorType="Gyroscope";
                break;
            case Sensor.TYPE_HEART_RATE:
                sensorType="Heart Rate";
                break;
            case Sensor.TYPE_LIGHT:
                sensorType="Light";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                sensorType ="Magnetic Field";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                sensorType ="Magnetic Unclibrated";
                break;
            case Sensor.TYPE_PROXIMITY:
                sensorType="Proximity";
                break;
            case Sensor.TYPE_ORIENTATION:
                sensorType="Orientation";
               break;
            case Sensor.TYPE_ROTATION_VECTOR:
                sensorType="Rotation Vector";
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                sensorType="Linear Acceleration";
                break;

            default:
                sensorType="Unknown";
        }
        return sensorType;
    }
}
