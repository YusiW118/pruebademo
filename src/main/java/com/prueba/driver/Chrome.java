package com.prueba.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome implements DriverStrategy{

    public WebDriver setStrategy(){

        System.out.println(System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        /* options.setExperimentalOption("userAutomationExtension", false); //set  an experimental option.
        options.addArguments("--no-sandbox");// Arguments used when the chrome is runned */

        return new ChromeDriver(options);
    }
    
}
