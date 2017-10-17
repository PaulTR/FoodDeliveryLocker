package com.paultrebilcoxruiz.lockbox;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.things.contrib.driver.pwmservo.Servo;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.Pwm;
import com.google.android.things.pio.PwmDriver;

import java.io.IOException;

public class MainActivity extends Activity {

    private BoxLock mLock;
    private BoxDisplay mDisplay;
    private CombinationUnlocker mUnlocker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLock = new BoxLock();
        mLock.closeLock();

        try {
            mDisplay = new BoxDisplay();
            mDisplay.clear();
            mDisplay.print( "Order #66" );
        } catch( IOException e ) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLock.close();

        try {
            mDisplay.close();
        } catch( Exception e ) {

        }
    }
}
