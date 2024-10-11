package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class TestSignIn{
    WebDriver driver;

    @org.testng.annotations.Test
    //Calling TC from TestHomePage
    public void HomePage(){
        TestHomePage homePage = new TestHomePage();
        homePage.beforeClass();
//        WebElement SignInButton = homePage.driver.findElement(By.xpath("//a[@href='/users/sign_in']"));
//        SignInButton.click();
//        homePage.driver.quit();
    }

    @org.testng.annotations.Test
    public void SignIn(){
        TestHomePage homePage = new TestHomePage();
        WebElement SignInButton = homePage.driver.findElement(By.xpath("//a[@href='/users/sign_in']"));
        SignInButton.click();
    }
//
//    @AfterClass
//    public  void afterClass(){
//        TestHomePage homePage = new TestHomePage();
//        homePage.driver.quit();
//    }

}

