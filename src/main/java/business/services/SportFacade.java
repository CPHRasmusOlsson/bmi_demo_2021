package business.services;

import business.entities.Sport;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.SportMapper;

import java.util.List;

public class SportFacade {
    private SportMapper sportMapper;



    public SportFacade(Database database) {
        this.sportMapper = new SportMapper(database);
    }

    public List<Sport> getAllSports() throws UserException
    {
        return sportMapper.getAllSports();
    }

    public int deleteSport(int sportId) throws UserException
    {
        return sportMapper.deleteSport(sportId);
    }

    public Sport getSportById(int sportId) throws UserException
    {
        return sportMapper.getSportById(sportId);
    }

    public int updateSport(int sportId, String name) throws UserException
    {
        return sportMapper.updateSport(sportId,name);
    }
}
