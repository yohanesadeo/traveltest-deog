package com.juaracoding.traveltest.formtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactInformationFormTest {
    private WebDriver driver;

    // Locators untuk elemen-elemen di halaman
    private final By firstNameInput = By.name("firstName");
    private final By lastNameInput = By.name("lastName");
    private final By phoneInput = By.name("phone");
    private final By emailInput = By.id("userName");
    
    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        driver.manage().window().maximize();
    }

    @Test
    public void testFillContactInformationForm() {
        // Data untuk diinput
        String firstName = "Yohanes";
        String lastName = "Adeo";
        String phone = "08123456789";
        String email = "adeo@test.com";

        
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(emailInput).sendKeys(email);

        // Validasi (Assertion) untuk memastikan data terisi dengan benar
        Assert.assertEquals(driver.findElement(firstNameInput).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(lastNameInput).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(phoneInput).getAttribute("value"), phone);
        Assert.assertEquals(driver.findElement(emailInput).getAttribute("value"), email);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
