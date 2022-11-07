package com.prueba.driver;


public class DriverStrategyImplement {

    public static int valor=0;
     //Starting chrome
     public static DriverStrategy chooseStrategy(String strategy){

        switch(strategy){
            case "Chrome":
            return new Chrome();
            default:
            return null;
        }
    }
}
