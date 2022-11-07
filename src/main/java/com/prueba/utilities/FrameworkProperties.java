package com.prueba.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrameworkProperties {
    private String result="";
    private InputStream inputStream;

    public String getProperty(String key) throws IOException{
        try {
            Properties properties=new Properties();
            String propFileName=Constants.PROP_FILE_NAME;
            inputStream=getClass().getClassLoader().getResourceAsStream(propFileName); //consigue los datos de framework.properties.
            System.out.println(inputStream);
            if(inputStream!=null){
                properties.load(inputStream); //Carga los datos 
            }else{
                throw new FileNotFoundException(Constants.FILE_NOT_EXCEPTION_MESSAGE);
            }
            String propertyValue=properties.getProperty(key);
            this.result=propertyValue;
        } catch (Exception e) {
            System.out.println("exception");
           e.printStackTrace();
        }

        return result;
    }
}
