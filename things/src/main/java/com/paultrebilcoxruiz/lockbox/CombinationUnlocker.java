package com.paultrebilcoxruiz.lockbox;


import android.text.TextUtils;
import android.util.Log;

import com.google.android.things.contrib.driver.button.Button;

import java.io.IOException;

public class CombinationUnlocker {

    private String PIN_BLUE = "BCM14";
    private String PIN_GREEN = "BCM15";
    private String PIN_YELLOW = "BCM23";

    private Button mBlueButton;
    private Button mGreenButton;
    private Button mYellowButton;

    private int mNumberOfAttempts = 3;
    private CombinationCallback mCallback;

    private String mCombination;
    private String mNeededCombinationKeys;

    public CombinationUnlocker() {

        try {
            mBlueButton = new Button(PIN_BLUE, Button.LogicState.PRESSED_WHEN_HIGH);
            mBlueButton.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button button, boolean b) {
                    if( !b ) {
                        checkKeyInCombination('b');
                    }
                }
            });

            mGreenButton = new Button(PIN_GREEN, Button.LogicState.PRESSED_WHEN_HIGH);
            mGreenButton.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button button, boolean b) {
                    if( !b ) {
                        checkKeyInCombination('g');
                    }
                }
            });

            mYellowButton = new Button(PIN_YELLOW, Button.LogicState.PRESSED_WHEN_HIGH);
            mYellowButton.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button button, boolean b) {
                    if( !b ) {
                        checkKeyInCombination('y');
                    }
                }
            });

        } catch( IOException e ) {

        }

    }

    private void checkKeyInCombination(char key) {
        Log.e("Test", "check key: " + key);
        if( !TextUtils.isEmpty(mNeededCombinationKeys) && !TextUtils.isEmpty(mCombination) && mNumberOfAttempts >= 1 ) {
            if( mNeededCombinationKeys.toLowerCase().charAt(0) == key) {
                mNeededCombinationKeys = new StringBuilder(mNeededCombinationKeys).deleteCharAt(0).toString();
            } else {
                mNeededCombinationKeys = mCombination;
                mNumberOfAttempts--;
            }

            if( TextUtils.isEmpty(mNeededCombinationKeys) ) {
                mCallback.onCombinationSuccess();
            } else if( mNumberOfAttempts == 0 ) {
                mCallback.onCombinationFailed();
            }
        }
    }

    public void close() throws IOException {
        mBlueButton.close();
        mGreenButton.close();
        mYellowButton.close();
    }

    public void setCallbacks(CombinationCallback callbacks) {
        mCallback = callbacks;
    }

    public void setCombination(String combination) {
        mCombination = combination;
        mNeededCombinationKeys = combination;
    }

    public interface CombinationCallback {
        void onCombinationSuccess();
        void onCombinationFailed();
    }

}
