package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.BmiFacade;
import business.services.BmiUtil;
import business.services.SportFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CalcBMICommand extends  CommandUnprotectedPage {

    private BmiFacade bmiFacade;
    private SportFacade sportFacade;

    public CalcBMICommand(String pageToShow) {

        super(pageToShow);
        this.bmiFacade = new BmiFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();
        User user;
        int user_id =1;

        if(session.getAttribute("user")!= null)
        {
         user = (User)session.getAttribute("user");
        }


        double height = 0.0;
        double weight = 0.0;
        double bmi = 0.0;
        String category ="";
        String gender = request.getParameter("gender");
        int sport_id = Integer.parseInt(request.getParameter("sport"));

        String[] hobbies =request.getParameterValues("hobby");
        List<String> hobbyListStrings = null;

        if(hobbies != null){
            hobbyListStrings = Arrays.asList(hobbies);
        }

        List<Integer> hobbyListIntegers = new ArrayList<>();
        for(String hobbyListItem:hobbyListStrings){
            hobbyListIntegers.add(Integer.parseInt(hobbyListItem));
        }


        try {
            height = Double.parseDouble(request.getParameter("height"));
            weight = Double.parseDouble(request.getParameter("weight"));
        } catch (NumberFormatException e){
            request.setAttribute("error","Husk at du skal indtaste 2 heltal i formularen");
            return "index";
            //throw new UserException("Husk at du skal indtaste 2 heltal i formularen");
        }

        bmi = BmiUtil.calcBMI(height,weight);
        category = BmiUtil.getCategory(bmi);

        request.setAttribute("bmi",bmi);
        request.setAttribute("height",height);
        request.setAttribute("weight",weight);
        request.setAttribute("category",category);
        request.setAttribute("gender",gender);
        request.setAttribute("sport_id",sport_id);
        request.setAttribute("hobbies", hobbyListIntegers);

        bmiFacade.insertBmiEntry(
                bmi,
                height,
                weight,
                category,
                gender,
                sport_id,
                user_id,
                hobbyListIntegers);


        return pageToShow;
    }
}
