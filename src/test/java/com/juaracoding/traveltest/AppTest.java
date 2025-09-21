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
        // inisialisasi WebDriverWait dengan durasi timeout 10 detik
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        String passwordInput = "123qwe";
        String confirmPasswordInput = "123qwe";

        // isi form
        // manggil wait, beda dari sleep, wait lebih efisien (tanya gpt :V)
        // tunggu sampe elementnya muncul, baru diisi
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
        // ada wwaitnya karena butuh waktu untuk load halamannya
        // kenapa pakai xpath? karena ini bukan di dalam tag tertentu, jadi pake xpath
        // dan pake contains biar lebih fleksibel (nggak harus sama persis)
        // penjelasan dalam () xpathnya
        // //*: cari di seluruh dokumen
        // [contains(text(),'Dear " + firstNameInput + "')
        // : cari element yang mengandung teks 'Dear [firstName].'
        String expectedGreetingText = "Dear " + firstNameInput + " " + lastNameInput + ",";
        WebElement greetingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Dear " + firstNameInput + "')]")));

        // Validasi pesan "Note: Your user name is..."
        // ada wwaitnya karena butuh waktu untuk load halamannya
        // penjelasan dalam () xpathnya
        // //*: cari di seluruh dokumen
        // [contains(text(),'Note: Your user name is " + emailInput + ".')]
        // : cari element yang mengandung teks 'Note: Your user name is [email].'
        String expectedNoteText = "Note: Your user name is " + emailInput + ".";
        WebElement noteElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Note: Your user name is')]")));

        // penjelasan contains
        // contains itu ngecek apakah sebuah string mengandung substring tertentu
        // atau tidak. Misalnya, contains("Hello, world!", "world") akan mengembalikan
        // true karena "Hello, world!" mengandung "world".
        Assert.assertEquals(greetingElement.getText(), expectedGreetingText);
        Assert.assertEquals(noteElement.getText(), expectedNoteText);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
