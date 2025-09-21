package com.juaracoding.traveltest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverService {
    public WebDriver getDriverChrome() {
        return new ChromeDriver();
    }
}
