package web.commands;

import business.entities.Sport;
import business.exceptions.UserException;
import business.services.BmiFacade;
import business.services.SportFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class ManageSportCommand extends CommandProtectedPage{
    private SportFacade sportFacade;



    public ManageSportCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.sportFacade = new SportFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        String delete = request.getParameter("delete");
        String editId = request.getParameter("edit");
        String update = request.getParameter("update");
        if(delete!=null)
        {
            int rowsAffected = sportFacade.deleteSport(Integer.parseInt(delete));
            if(rowsAffected>0)
            {
                request.getServletContext().setAttribute("sportList", sportFacade.getAllSports());
            }
            else
            {
                request.setAttribute("error","Du kan ikke fjerne denne sportsgren, da den er i brug");
            }
        } else if(editId != null)
        {
            Sport sport = sportFacade.getSportById(Integer.parseInt(editId));
            request.setAttribute("sportItem",sport);
            return "editsportspage";
        } else if( update != null)
        {
            ////((88))\\\\
            String name = request.getParameter("name");
            String sportId = request.getParameter("sports_id");
            int rowsInserted = sportFacade.updateSport(Integer.parseInt(sportId),name);
            if(rowsInserted==1)
            {
                request.getServletContext().setAttribute("sportList",sportFacade.getAllSports());
            }
        }

        return pageToShow;
    }

}
