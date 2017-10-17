package com.paultrebilcoxruiz.lockbox;

public class CombinationUnlocker {

    private String PIN_BLUE;
    private String PIN_GREEN;
    private String PIN_YELLOW;

    private int mNumberOfAttempts = 3;

    public CombinationUnlocker() {

    }

    public interface combinationCallback {
        void onCombinationSuccess();
        void onCombinationFailed();
    }
}
