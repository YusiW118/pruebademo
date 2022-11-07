package com.prueba.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.prueba.driver.DriverSingleton;
import com.prueba.utilities.Constants;
import com.prueba.utilities.Screenshot;

public class Contact {
    public WebDriver driver;
    private String number;

    public Contact(){
        this.driver=DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(xpath = "//*[@id='root']/div/div/header/div/div/div/div/div/nav/div/a[7]")
    private WebElement contactLink;
    
    @FindBy(xpath = "//*[@id='root']/div/div/div[1]/div/div[1]/div/div/div/div/p[2]/span/span[2]")
    private WebElement telefono;

    @FindBy(xpath= "/html/body/div/div/div/div[3]/div/div/div[2]/div[2]/nav/ul/div[3]/a")
    private WebElement avisoLegal;
    @FindBy(xpath ="//*[@id='root']/div/div/div[3]/div/div/div[2]/div[2]/nav/ul/div[3]/a/li")
    private WebElement aviso1;

    @FindBy(xpath="//*[@id='root']/div/div/div[1]/div/div/p[3]/span/span")
    private WebElement numeroAviso;

    @FindBy(id = "rcc-confirm-button")
    private WebElement cookies;

    public Boolean verificarNumero(){
        cookies.click();
        contactLink.click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(telefono));
        number=telefono.getText().replace("(+34)", "").trim();
        System.out.println(number);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", aviso1);
        wait.until(ExpectedConditions.visibilityOf(avisoLegal));
        jse.executeScript("arguments[0].click();", avisoLegal);
        wait.until(ExpectedConditions.visibilityOf(numeroAviso));
        System.out.println(numeroAviso.getText());
        if(numeroAviso.getText().contains(number)){
            return true;
        }else{
            return false;
        }
    }

    @FindBy(xpath = "//*[contains(text(),'Faraday')]")
    private List< WebElement> Faradays;

    public int getNumberfaraday(){
        cookies.click();
        contactLink.click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mapDiv']//child::div[contains(text(),'Faraday')]")));
        System.out.println(Faradays.size());
        return Faradays.size() + 1;
    }

    @FindBy(xpath = "//*[@id='maps']/div/div/form/button")
    private WebElement enviarFormulario;

    @FindBy(xpath = "//*[@id='maps']/div/div/form/div[6]/p")
    private WebElement campoRequerido;

    public void verificarCampoRequerido(){
        cookies.click();
        contactLink.click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(telefono));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", enviarFormulario);
        jse.executeScript("arguments[0].click();", enviarFormulario);
        wait.until(ExpectedConditions.visibilityOf(campoRequerido));
        if(campoRequerido.getText().contains(Constants.CAMPOREQUERIDO)){
            String name= new Object(){}.getClass().getEnclosingMethod().getName();//This function gets the name of the currently executing method
                Screenshot.takeScreenShot(name);
        }else{
            System.out.println("Algo ha ocurrido");
        }

    }
    private List<WebElement> fechas;
        
    @FindBy(xpath = "//*[@id='root']/div/div/div[2]/div[2]/div[1]/p[2]")
    private WebElement noticia;

    public boolean verificarData(){
        cookies.click();
        contactLink.click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", noticia);
        this.fechas = driver.findElements(By.xpath("//p[contains(text(),'noticias')]//parent::div//following-sibling::div//child::article//section//div//p[2]"));
        LocalDate mesanterior=LocalDate.now().minusMonths(2);
        LocalDate mesactual=LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (WebElement fecha : fechas) {
            String fechavalue=fecha.getText();
            LocalDate date=LocalDate.from(formatter.parse(fechavalue));
            if(date.isAfter(mesanterior) && date.isBefore(mesactual)){
                System.out.println("El mes corresponde: " + date.toString());
            }else{
                return false;
            }
            
        }
        return true;
    }
}
