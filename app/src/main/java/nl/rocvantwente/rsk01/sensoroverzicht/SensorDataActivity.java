package nl.rocvantwente.rsk01.sensoroverzicht;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SensorDataActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager mSensorManager;
    Sensor mSensorLight , mSensorProximity;
    TextView mTextSensorLight , mTextSensorProximity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mTextSensorLight = (TextView)findViewById(R.id.label_light);
        mTextSensorProximity = (TextView)findViewById(R.id.label_proximity);
        if(mSensorLight == null){
            mTextSensorLight.setText(getString(R.string.error_no_sensor));
        }

        if(mSensorProximity == null){
            mTextSensorProximity.setText(getString(R.string.error_no_sensor));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mSensorLight != null) {
            mSensorManager.registerListener(this , mSensorLight ,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(mSensorProximity != null) {
            mSensorManager.registerListener(this , mSensorProximity ,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float currentValue = event.values[0];
        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                // Handle light sensor
                mTextSensorLight.setText(getString(R.string.value_light , currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                // Handle proximity sensor
                mTextSensorProximity.setText(getString(R.string.value_proximity , currentValue));
                break;
            default:
                // Do nothing
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
