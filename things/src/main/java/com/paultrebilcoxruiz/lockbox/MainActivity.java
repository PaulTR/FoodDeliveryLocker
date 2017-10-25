package com.paultrebilcoxruiz.lockbox;

import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class MainActivity extends Activity implements CombinationUnlocker.CombinationCallback {

    private static final String BOX_URL = "https://lockbox-1750e.firebaseio.com/devices/";

    private BoxLock mLock;
    private BoxDisplay mDisplay;
    private CombinationUnlocker mUnlocker;

    private DatabaseReference mDatabaseRef;
    private BoxConfig mConfig;

    private static final String DEVICE_UUID = "12345";//Make an actual UUID later

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLock();
        initDisplay();
        initUnlocker();

        initDatabase();
    }

    private void initDefaults() {
        if( mConfig != null ) {
            if( mDisplay != null ) {
                try {
                    mDisplay.clear();
                    mDisplay.print("Order #" + mConfig.getOrderNumber() );
                } catch( IOException e ) {

                }
            }

            if( mUnlocker != null ) {
                mUnlocker.setCombination(mConfig.getCombination());
            }
        }

    }

    private void initLock() {
        mLock = new BoxLock();
        mLock.closeLock();
    }

    private void initDisplay() {
        try {
            mDisplay = new BoxDisplay();
            mDisplay.clear();
        } catch( IOException e ) {

        }
    }

    private void initUnlocker() {
        mUnlocker = new CombinationUnlocker();
        mUnlocker.setCallbacks(this);
    }

    private void initDatabase() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl(BOX_URL + DEVICE_UUID );
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mConfig = dataSnapshot.getValue(BoxConfig.class);

                initDefaults();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            mLock.close();
            mDisplay.close();
            mUnlocker.close();
        } catch( Exception e ) {

        }

    }


    @Override
    public void onCombinationSuccess() {
        mLock.openLock();
        try {
            mDisplay.clear();
            mDisplay.setCursor(0, 0);
            mDisplay.print("Enjoy!");
        } catch( IOException e ) {

        }
    }

    @Override
    public void onCombinationFailed() {
        try {
            mDisplay.clear();
            mDisplay.print("Out of attempts");
            mDisplay.setCursor(0, 1);
            mDisplay.print("Will not unlock");
        } catch( IOException e ) {

        }
    }

}
