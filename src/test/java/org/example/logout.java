package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.HomePage;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class logout {
     WebDriver driver;
     LoginPage loginPage;
     WebDriverWait wait;
     HomePage homePage;


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.get("https://qajobbyapp.ccbp.tech/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void logOutFunctionality(){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,expectedUrl,"URLs do not match");
        Assert.assertTrue(homePage.isLogOutButtonDisplayed());
        homePage.logout();
        Alert al = driver.switchTo().alert();
        al.accept();
        wait.until(ExpectedConditions.urlToBe("https://qajobbyapp.ccbp.tech/login"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://qajobbyapp.ccbp.tech/login","URL is not matching");
    }
}
