package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.time.Duration;

public class TestHomePage {
    WebDriver driver;
    String osName = System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        if (osName.contains("Windows")){
            System.setProperty("webdriver.gecko.driver", projectPath +"\\browserDrivers\\chromedriver.exe");
        }
        else {
            System.setProperty("webdriver.gecko.driver", projectPath+"/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://docerfy.com/");
    }

    @org.testng.annotations.Test
    public void TC_01_CheckURL(){
        //CheckURL
        Assert.assertEquals(driver.getCurrentUrl(),"https://docerfy.com/");
    }

    @org.testng.annotations.Test
    public void TC_02_CheckLogo(){
        //CheckLogo
        Assert.assertTrue(driver.findElement(By.cssSelector("img.logo")).isDisplayed());
    }

    @org.testng.annotations.Test
    public void TC_03_CheckText(){
        //CheckTextInHomePage
        WebElement h3 = driver.findElement(By.xpath("//h3[@class='title fs-30 font-weight-bold']"));
        String actualText = h3.getText();
        String expectedText = "The Ultimate Digital Gatekeeper!\nAccess Granted Through Facial Recognition!";
        Assert.assertEquals(actualText, expectedText,"Text is not displayed correctly");
    }


    @AfterClass
    public  void afterClass(){
        driver.quit();
    }

}