package com.app.sensorimpl;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    ImageView mDrawable, ivImg;
    public static int x = 0;
    public static int y = 0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawable = (ImageView) findViewById(R.id.ivImage);
        ivImg = (ImageView) findViewById(R.id.ivImg);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        textView = findViewById(R.id.textView);

       /* float centreX = (float) mDrawable.getX() + mDrawable.getWidth() / 2;
        float centreY = (float) mDrawable.getY() + mDrawable.getHeight() / 2;*/
        int mWidth = this.getResources().getDisplayMetrics().widthPixels;
        int mHeight = this.getResources().getDisplayMetrics().heightPixels;

        Log.e("centreX", "" + mWidth);
        Log.e("centreY", "" + mHeight);
        ivImg.setPivotX(100);
        ivImg.setPivotY(100);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            x -= (int) event.values[0];
            y += (int) event.values[1];

           /* mDrawable.setY(y);
            mDrawable.setX(x);
            Log.e("X", "" + x);
            Log.e("y", "" + y);*/

            float x = event.values[0];
            float y = event.values[1];
            if (Math.abs(x) > Math.abs(y)) {
                if (x < 0) {
                    //image.setImageResource(R.drawable.right);
                    textView.setText("You tilt the device right");
                }
                if (x > 0) {
                    //image.setImageResource(R.drawable.left);
                    textView.setText("You tilt the device left");
                }
            } else {
                if (y < 0) {
                    //image.setImageResource(R.drawable.up);
                    textView.setText("You tilt the device up");
                }
                if (y > 0) {
                    //image.setImageResource(R.drawable.down);
                    textView.setText("You tilt the device down");
                }
            }
            if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
                //image.setImageResource(R.drawable.center);
                textView.setText("Not tilt device");
            }

            Log.e("X", "" + x);
            Log.e("y", "" + y);
        }
    }
}