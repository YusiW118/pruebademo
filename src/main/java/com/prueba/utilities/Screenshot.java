package com.prueba.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.util.FileCopyUtils;

import com.prueba.driver.DriverSingleton;

public class Screenshot {
    public static boolean takeScreenShot(String testcase){
        File file=((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate=LocalDate.now();
        
        try{
            FileCopyUtils.copy(file, new File(Constants.SCREEN_FOLDER+testcase+"_"+
                                           dtf.format(localDate)+Constants.EXTENSION));
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
        
    }
}
