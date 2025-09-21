package com.juaracoding.traveltest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverService {

    private WebDriver driver;
    
    public WebDriver getDriverChrome() {
        return driver = new ChromeDriver();
    }
}
