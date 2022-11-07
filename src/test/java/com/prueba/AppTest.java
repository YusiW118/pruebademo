package com.prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.prueba.driver.DriverSingleton;
import com.prueba.pages.Contact;
import com.prueba.utilities.Constants;
import com.prueba.utilities.FrameworkProperties;

public class AppTest 
{
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static Contact contact;
    @Before
    public void  initializedObject() throws IOException{
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        contact=new Contact();
        
    }

    @Test
    public void test1()
    {
        driver.get(Constants.URL);
        assertEquals(true, contact.verificarNumero());
    }
    @Test
    public void test2(){
        driver.get(Constants.URL);
        assertEquals(Constants.number, contact.getNumberfaraday());
    }
    @Test
    public void test3(){
        driver.get(Constants.URL);
        contact.verificarCampoRequerido();
    }

    @Test
    public void test4(){
        driver.get(Constants.URL);
        assertEquals(true,contact.verificarData());
    }

    @AfterClass
    public static void closeObject(){
       // driver.close();
    }
}
