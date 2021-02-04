package selenium;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    private WebDriver driver;

    @Before
    public void setUp(){
        System.out.println("Iniciando configuración...");

        

        switch (selenium.Util.getOS()) {
            case WINDOWS:
                //do windows stuff
                System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                break;
            case LINUX:
                System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
                 break;
             default:
             break;
        }

        //System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        
        driver = new ChromeDriver(options);
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        driver.navigate().to("https://www.google.com");
    }

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException
    {
        System.out.println("Iniciando Pruebas...");
        Thread.sleep(2000);
        WebElement searchbox = driver.findElement(By.name("q"));
        Thread.sleep(2000);
        searchbox.sendKeys("HandBook Devops");
        searchbox.submit();
        Thread.sleep(2000);

        /*WebElement libro = driver.findElement(By.xpath("//*[@id=\"hdtb-msb-vis\"]/div[5]/a"));
        libro.click();*/

        WebElement compra = driver.findElements(By.className("LC20lb")).get(0);
        Thread.sleep(2000);
        compra.click();
        Thread.sleep(2000);

        System.out.println("El titulo es: " + driver.getTitle());

        assertEquals("Amazon.com: The DevOps Handbook: How to Create World-Class Agility, Reliability, and Security in Technology Organizations (9781942788003): Kim, Gene, Debois, Patrick, Willis, John, Humble, Jez, Allspaw, John: Books", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
