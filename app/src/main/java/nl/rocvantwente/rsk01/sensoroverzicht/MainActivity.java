package nl.rocvantwente.rsk01.sensoroverzicht;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder("");
        for(Sensor currentSensor : sensorList) {
            sensorText.append(currentSensor.getName()).append(System.getProperty("line.separator"));
        }
        TextView sensorTextView = (TextView)findViewById(R.id.sensor_list);
        sensorTextView.setText(sensorText);
    }
}
