package web.commands;

import business.exceptions.UserException;
import business.services.BmiFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class MangeSportCommand extends CommandProtectedPage{
    BmiFacade bmiFacade;

    public MangeSportCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.bmiFacade = new BmiFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        String delete = request.getParameter("delete");
        if(delete!=null)
        {
            int rowsAffected = bmiFacade.deleteSport(Integer.parseInt(delete));
            if(rowsAffected>0)
            {
                request.getServletContext().setAttribute("sportList",bmiFacade.getAllSports());
            }
            else
            {
                request.setAttribute("error","Du kan ikke fjerne denne sportsgren, da den er i brug");
            }
        }

        return pageToShow;
    }

}
