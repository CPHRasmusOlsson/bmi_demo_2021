package business.services;

import java.text.DecimalFormat;

public class BmiUtil {
    private static DecimalFormat df2 = new DecimalFormat("#.##");


    public static Double calcBMI(Double height, Double weight){

        return Double.parseDouble(df2.format(weight /((height / 100) * (height / 100))));
    }

    public static String getCategory(Double bmi){

        if (bmi>30){
            return "svært overvægtig";
        } else if (bmi < 18.5){
            return "undervægtig";
        } else if (bmi<25){
            return "normalvægtig";
        }else{
            return "overvægtig";
        }
    }

}
