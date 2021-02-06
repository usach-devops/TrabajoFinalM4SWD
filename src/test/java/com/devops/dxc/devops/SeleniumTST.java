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

import com.devops.dxc.selenium.UtilSelenium;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.devops.dxc.devops.model.Util;

@SpringBootTest
public class SeleniumTST {
    private static int valUF;
    private static WebDriver driver;

    @BeforeAll
    static void contextLoads() {
        valUF = Util.getUf();
        setUp();
    }

    static void setUp() {
        System.out.println("Iniciando configuración...");

        switch (UtilSelenium.getOS()) {
            case WINDOWS:
                // do windows stuff
                System.setProperty("webdriver.chrome.driver", "C:\\selenium\\drivers\\chromedriver.exe");
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
                break;
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://192.81.214.49/");
        driver.manage().window().maximize();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
    }

    @Order(1)
    @ParameterizedTest(name = "ahorro = {0} --> valor fondo invalido ")
    @CsvSource({ "''", "0"

    })
    void ValorFondoInvalido(String ahorro) throws InterruptedException {
        System.out.println("Iniciando ValorFondoInvalido...");
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("iAhorro")).click();
        driver.findElement(By.id("iAhorro")).sendKeys(ahorro);
        driver.findElement(By.id("iSueldo")).click();
        driver.findElement(By.id("iSueldo")).sendKeys("1000000");
        driver.findElement(By.id("btnSubmit")).click();
        Thread.sleep(2000);
        String diezResponse = driver.findElement(By.xpath("//span[@id='diez']")).getText();
        System.out.println("diezResponse = " + diezResponse);
        assertTrue("Error en ahorro " + ahorro, diezResponse.trim().equals("") || Integer.parseInt(diezResponse) == 0);
        return;
    }

    @Order(2)
    @ParameterizedTest(name = "ahorro = {0} , sueldo {1}, retiro saldo ")
    @CsvSource({
            /*
             * " , ", "0, 0",
             */
            "900000, 1000000"/*
                              * , "1100000, 1000000", "5000000, 1500000", "44000000, 1500000",
                              * "50000000, 2500000"
                              */
    })
    void RetirarSaldo(String ahorro, String sueldo) throws InterruptedException {
        System.out.println("Iniciando RetirarSaldo...");
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("iAhorro")).click();
        driver.findElement(By.id("iAhorro")).sendKeys(ahorro);
        driver.findElement(By.id("iSueldo")).click();
        driver.findElement(By.id("iSueldo")).sendKeys(sueldo);
        driver.findElement(By.id("btnSubmit")).click();
        Thread.sleep(2000);
        String diezResponse = driver.findElement(By.xpath("//span[@id='diez']")).getText();
        System.out.println("diezResponse = " + diezResponse);

        assertEquals(Integer.parseInt(ahorro), Integer.parseInt(diezResponse));
    }

    @Order(3)
    @ParameterizedTest(name = "ahorro = {0} , sueldo {1}, retiro {2} UF ")
    @CsvSource({
            /*
             * " , , ", "0, 0, 0",
             */
            "900000, 1000000, 150", "1100000, 1000000, 150", "5000000, 1500000, 150", "5000000, 1000000, 150" })
    void NoPuedeRetirar150UF(String ahorro, String sueldo, int retiroUF) throws InterruptedException {
        System.out.println("Iniciando NoPuedeRetirar150UF...");
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("iAhorro")).click();
        driver.findElement(By.id("iAhorro")).sendKeys(ahorro);
        driver.findElement(By.id("iSueldo")).click();
        driver.findElement(By.id("iSueldo")).sendKeys(sueldo);
        driver.findElement(By.id("btnSubmit")).click();
        Thread.sleep(2000);

        int esperado = retiroUF * valUF;
        String diezResponse = driver.findElement(By.xpath("//span[@id='diez']")).getText();
        System.out.println("diezResponse = " + diezResponse);

        assertNotEquals(esperado, Integer.parseInt(diezResponse));

        return;
    }

    @Order(4)
    @ParameterizedTest(name = "ahorro = {0} , sueldo {1}, retiro {2} UF ")
    @CsvSource({
            /*
             * " , , ", "0, 0, 0",
             */
            "44000000, 1500000, 150", "50000000, 2500000, 150" })
    void PuedeRetirar150UF(String ahorro, String sueldo, int retiroUF) throws InterruptedException {
        System.out.println("Iniciando Pruebas...");
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("iAhorro")).click();
        driver.findElement(By.id("iAhorro")).sendKeys(ahorro);
        driver.findElement(By.id("iSueldo")).click();
        driver.findElement(By.id("iSueldo")).sendKeys(sueldo);
        driver.findElement(By.id("btnSubmit")).click();
        Thread.sleep(2000);

        int esperado = retiroUF * valUF;

        String diezResponse = driver.findElement(By.xpath("//span[@id='diez']")).getText();
        System.out.println(diezResponse);

        assertEquals(esperado, Integer.parseInt(diezResponse));
        return;
    }

    @Order(5)
    @ParameterizedTest(name = "ahorro = {0} , sueldo {1}, saldo cero ")
    @CsvSource({
            /*
             * " , ", "0, 0",
             */
            "900000, 1000000" })
    void SaldoCero(String ahorro, String sueldo) throws InterruptedException {
        System.out.println("Iniciando SaldoCero...");
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("iAhorro")).click();
        driver.findElement(By.id("iAhorro")).sendKeys(ahorro);
        driver.findElement(By.id("iSueldo")).click();
        driver.findElement(By.id("iSueldo")).sendKeys(sueldo);
        driver.findElement(By.id("btnSubmit")).click();
        Thread.sleep(2000);

        String saldoResponse = driver.findElement(By.xpath("//span[@id='saldo']")).getText();
        System.out.println("saldoResponse = " + saldoResponse);

        assertEquals(0, Integer.parseInt(saldoResponse));
        return;
    }

    @Order(6)
    @ParameterizedTest(name = "ahorro = {0} , sueldo {1}, saldo cero ")
    @CsvSource({ "5000000, 1500000", "44000000, 1500000", "50000000, 2500000", "1100000, 1000000" })
    void SaldoMayorACero(String ahorro, String sueldo) throws InterruptedException {
        System.out.println("Iniciando SaldoMayorACero...");
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("iAhorro")).click();
        driver.findElement(By.id("iAhorro")).sendKeys("");
        driver.findElement(By.id("iAhorro")).sendKeys(ahorro);
        driver.findElement(By.id("iSueldo")).click();
        driver.findElement(By.id("iSueldo")).sendKeys("");
        driver.findElement(By.id("iSueldo")).sendKeys(sueldo);
        driver.findElement(By.id("btnSubmit")).click();
        Thread.sleep(2000);

        // String sueldoResponse = driver.findElement(By.xpath("//input[@id='iSueldo']")).getAttribute("value");
        // String ahorroResponse = driver.findElement(By.xpath("//input[@id='iAhorro']")).getAttribute("value");

        String saldoResponse = driver.findElement(By.xpath("//span[@id='saldo']")).getText();

        System.out.println("saldoResponse = " + saldoResponse);
        // System.out.println("ahorro = " + ahorroResponse);
        // System.out.println("sueldo = " + sueldoResponse);

        assertTrue("Error en Saldo " + saldoResponse, Integer.parseInt(saldoResponse) > 0);
    }

    @Order(7)
    @ParameterizedTest(name = "ahorro = {0} , sueldo {1}, paga Impuesto ")
    @CsvSource({
            /* " , ", */
            "0, 0", "900000, 1000000", "1100000, 1000000", "6000000, 2500000", "44000000, 1500000", "50000000, 2500000",
            "50000000, 1499999", "50000000, 1500000", "50000000, 1529999", "50000000, 1530000", "50000000, 1530001",
            "50000000, 2549999", "50000000, 2550000", "50000000, 2550001", "50000000, 3569999", "50000000, 3570000",
            "50000000, 3570001", "50000000, 4589999", "50000000, 4590000", "50000000, 4590001", "50000000, 6119999",
            "50000000, 6120000", "50000000, 6120001", "50000000, 15817999", "50000000, 15818000",
            "50000000, 15818001" })
    void PagaImpuesoCaso1(String ahorro, String sueldo) throws InterruptedException {
        System.out.println("Iniciando PagaImpuesoCaso1...");
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("iAhorro")).click();
        driver.findElement(By.id("iAhorro")).sendKeys(ahorro);
        driver.findElement(By.id("iSueldo")).click();
        driver.findElement(By.id("iSueldo")).sendKeys(sueldo);
        driver.findElement(By.id("btnSubmit")).click();
        Thread.sleep(2000);

        String impuestoResponse = driver.findElement(By.xpath("//span[@id='impuesto']")).getText();
        System.out.println("impuestoResponse = " + impuestoResponse);

        assertTrue("Error en Impuesto ", Integer.parseInt(impuestoResponse) >= 0);
        return;
    }

    @AfterAll
    static void Fin() {
        driver.quit();
    }

}
