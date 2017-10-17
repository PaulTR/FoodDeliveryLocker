package com.paultrebilcoxruiz.lockbox;

import com.google.android.things.contrib.driver.pwmservo.Servo;

import java.io.IOException;

public class BoxLock {
    private Servo mServo;
    private double mOpenAngle = 0;
    private double mClosedAngle = 120;

    public BoxLock() {
        try {
            mServo = new Servo("PWM1");
            mServo.setEnabled(true);
            mServo.setAngleRange(0f, 180f);
            mServo.setAngle(mClosedAngle);
        } catch (IOException e) {}
    }

    public void openLock() {
        try {
            mServo.setAngle(mOpenAngle);
        } catch( IOException e ) {

        }
    }

    public void closeLock() {
        try {
            mServo.setAngle(mClosedAngle);
        } catch (IOException e) {

        }
    }

    public void close() {
        try {
            mServo.close();
        } catch( IOException e ) {

        }
    }
}
