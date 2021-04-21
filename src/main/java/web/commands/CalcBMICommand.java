package web.commands;

import business.exceptions.UserException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcBMICommand extends  CommandUnprotectedPage {

    private static DecimalFormat df2 = new DecimalFormat("#.##");


    public CalcBMICommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        double height = 0.0;
        double weight = 0.0;
        double bmi = 0.0;
        String category ="";

        try {
            height = Double.parseDouble(request.getParameter("height"));
            weight = Double.parseDouble(request.getParameter("weight"));
        } catch (NumberFormatException e){
            throw new UserException("husk at du skal indtaste 2 heltal i formularen");
        }
        bmi = Double.parseDouble(df2.format(weight /((height / 100) * (height / 100))));


        if (bmi>30){
            category = "svært overvægtig";
        } else if (bmi < 18.5){
            category = "undervægtig";
        } else if (bmi<25){
            category  ="Normalvægtig";
        }else{
            category = "overvægtig";
        }

        request.setAttribute("bmi",bmi);
        request.setAttribute("height",height);
        request.setAttribute("weight",weight);
        request.setAttribute("category",category);



        return pageToShow;
    }
}
