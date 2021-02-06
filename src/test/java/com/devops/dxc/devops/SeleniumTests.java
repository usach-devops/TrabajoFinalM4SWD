package com.devops.dxc.devops;

// import static org.junit.Assert.assertEquals;
// //import static org.junit.Assert.assertTrue;

// import com.devops.dxc.selenium.UtilSelenium;

// import org.springframework.boot.test.context.SpringBootTest;
// import org.junit.Test;
// import org.junit.Before;
// import org.junit.After;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;
import com.devops.dxc.selenium.UtilSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class SeleniumTests 
{
    
    private static WebDriver driver;

@BeforeAll
    static void contextLoads() {
        setUp();
    }

   
    static void setUp(){
        System.out.println("Iniciando configuración...");

        OS os = UtilSelenium.getOS();

        System.out.println(os);

        switch (os) {
           
            case WINDOWS:
                //do windows stuff
                System.setProperty("webdriver.chrome.driver","C:\\selenium\\drivers\\chromedriver.exe");
                break;
            case LINUX:
                
                
                System.setProperty("webdriver.chrome.driver","/opt/chromedriver");
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
    @Order(1)
    void shouldAnswerWithTrue() throws InterruptedException 
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
        System.out.println("Fin Test");
        
    }

    @AfterAll
    static void Fin()
    {
        driver.quit();
    }
    


}
