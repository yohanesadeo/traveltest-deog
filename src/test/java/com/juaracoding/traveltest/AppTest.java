package com.juaracoding.traveltest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AppTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new DriverService().getDriverChrome();
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        driver.manage().window().maximize();
        // inisialisasi WebDriverWait dengan durasi timeout 25 detik
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    @Test
    public void registerUserTest() {
        // data untuk diinput
        String firstNameInput = "Yohanes";
        String lastNameInput = "Adeo";
        String phoneInput = "08123456789";
        String userNameInput = "adeo@test.com"; // e-mail

        String address1Input = "Jl. Paris";
        String cityInput = "Jogja";
        String stateInput = "DI Yogyakarta";
        String postalCodeInput = "55281";
        String countryInput = "INDONESIA";

        String emailInput = "adeo@test.com"; // username
        String passwordInput = "12345";
        String confirmPasswordInput = "12345";

        // isi form
        // manggil wait, beda dari sleep, wait lebih efisien (tanya gpt :V)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys(firstNameInput);
        driver.findElement(By.name("lastName")).sendKeys(lastNameInput);
        driver.findElement(By.name("phone")).sendKeys(phoneInput);
        driver.findElement(By.name("userName")).sendKeys(userNameInput);

        driver.findElement(By.name("address1")).sendKeys(address1Input);
        driver.findElement(By.name("city")).sendKeys(cityInput);
        driver.findElement(By.name("state")).sendKeys(stateInput);
        driver.findElement(By.name("postalCode")).sendKeys(postalCodeInput);
        driver.findElement(By.name("country")).sendKeys(countryInput);

        driver.findElement(By.name("email")).sendKeys(emailInput);
        driver.findElement(By.name("password")).sendKeys(passwordInput);
        driver.findElement(By.name("confirmPassword")).sendKeys(confirmPasswordInput);

        // Validasi bahwa semua input field terisi dengan benar sebelum submit
        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstNameInput);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastNameInput);
        Assert.assertEquals(driver.findElement(By.name("phone")).getAttribute("value"), phoneInput);
        Assert.assertEquals(driver.findElement(By.name("userName")).getAttribute("value"), userNameInput);
        Assert.assertEquals(driver.findElement(By.name("address1")).getAttribute("value"), address1Input);
        Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"), cityInput);
        Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"), stateInput);
        Assert.assertEquals(driver.findElement(By.name("postalCode")).getAttribute("value"), postalCodeInput);
        Assert.assertEquals(driver.findElement(By.name("country")).getAttribute("value"), countryInput);
        
        driver.findElement(By.name("submit")).click();

        // Validasi di halaman konfirmasi
        // Validasi pesan sapaan "Dear..."
        String expectedGreetingText = "Dear " + firstNameInput + " " + lastNameInput + ",";
        WebElement greetingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Dear " + firstNameInput + "')]")));

        // Validasi pesan "Note: Your user name is..."
        String expectedNoteText = "Note: Your user name is " + emailInput + ".";
        WebElement noteElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Note: Your user name is')]")));

        Assert.assertTrue(greetingElement.getText().contains(expectedGreetingText));
        Assert.assertTrue(noteElement.getText().contains(expectedNoteText));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
