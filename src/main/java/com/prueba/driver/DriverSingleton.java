package com.prueba.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class DriverSingleton {
    private static DriverSingleton instance=null;
    private static WebDriver driver;

    private DriverSingleton(String driver){
        instantiate(driver); //Internamente crea el navegador con sus configuraciones.
    }

    public WebDriver instantiate(String strategy){
        DriverStrategy driverStrategy=DriverStrategyImplement.chooseStrategy(strategy); //To create the object chrome
        driver=driverStrategy.setStrategy();//Calling the fuction
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //Give waiting time to run the chrome
        driver.manage().window().maximize();//Maximize the window.

        return driver;

    }

    public static DriverSingleton getInstance(String driver){
        if(instance==null){
            instance=new DriverSingleton(driver);
        }

        return instance;
    }

    public static void closeObjectInstance(){
        instance=null;
        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
    
}
