package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class TestSignIn{
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

    @Test
    public  void TC1_SignInWithIncorrectFormatEmail(){

        //Step sign in
        WebElement SignInButton = driver.findElement(By.xpath("//a[@href='/users/sign_in']"));
        SignInButton.click();
        WebElement email = driver.findElement(By.xpath("//input[@id='user_email']"));
        email.sendKeys("@yopmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='user_password']"));
        password.sendKeys("123456");

        //Verify error message display correctly
        WebElement EmailErrorMessage = driver.findElement(By.xpath("//label[@id='user_email-error']"));
        String actualMessage = EmailErrorMessage.getText();
        String expectedMessage = "Please enter a valid email address.";
        Assert.assertEquals(actualMessage,expectedMessage,"Text is not displayed correctly!");
    }

    @Test
    public  void TC2_SignInWithEmailDoesNotExistInSystem(){

        //Step sign in
//        WebElement SignInButton = driver.findElement(By.xpath("//a[@href='/users/sign_in']"));
//        SignInButton.click();
        WebElement email = driver.findElement(By.xpath("//input[@id='user_email']"));
        email.clear();
        email.sendKeys("dcdc111222@yopmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='user_password']"));
        password.clear();
        password.sendKeys("123456");
        WebElement LoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        LoginButton.click();

        //Verify error message display correctly
        WebElement EmailErrorMessage = driver.findElement(By.xpath("//span[contains(text(),'Invalid Email or password.')]"));
        String actualMessage = EmailErrorMessage.getText();
        String expectedMessage = "Invalid Email or password.";
        Assert.assertEquals(actualMessage,expectedMessage,"Text is not displayed correctly!");
    }


    @Test
    public void TC3_SignInWithCorrectAccount(){

        //Step sign in
//        WebElement SignInButton = driver.findElement(By.xpath("//a[@href='/users/sign_in']"));
//        SignInButton.click();

        WebElement email = driver.findElement(By.xpath("//input[@id='user_email']"));
        email.clear();
        email.sendKeys("dc205@yopmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='user_password']"));
        password.clear();
        password.sendKeys("123456");
        WebElement LoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        LoginButton.click();

        //Verify sign in successful
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ajs-message.ajs-success")));

        //Get the text of the alertify success message
        String actualMessage = successMessage.getText();
        //Expected message
        String expectedMessage = "Signed in successfully.";
        //Assert that the message is displayed as expected
        Assert.assertEquals(actualMessage,expectedMessage,"Text is not displayed correctly!");
    }

    @AfterMethod
    public void waitBetweenTests() throws InterruptedException {
        // Wait for 5 seconds between test cases
        Thread.sleep(3000); // 5000 milliseconds = 5 seconds
    }

    @AfterClass
    public  void afterClass(){
        driver.quit();
    }

}

