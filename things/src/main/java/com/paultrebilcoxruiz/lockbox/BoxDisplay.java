package com.paultrebilcoxruiz.lockbox;

import com.nilhcem.androidthings.driver.lcd1602a.Lcd1602;

import java.io.IOException;

public class BoxDisplay extends Lcd1602 {

    private static final String GPIO_LCD_RS = "BCM26";
    private static final String GPIO_LCD_EN = "BCM19";

    private static final String GPIO_LCD_D4 = "BCM21";
    private static final String GPIO_LCD_D5 = "BCM20";
    private static final String GPIO_LCD_D6 = "BCM16";
    private static final String GPIO_LCD_D7 = "BCM12";

    public BoxDisplay() throws IOException {
        super(GPIO_LCD_RS, GPIO_LCD_EN, GPIO_LCD_D4, GPIO_LCD_D5, GPIO_LCD_D6, GPIO_LCD_D7);
        begin(16, 2);
    }
}
