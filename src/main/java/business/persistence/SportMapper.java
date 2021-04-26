package business.persistence;


import business.entities.Sport;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SportMapper {

    private Database database;



    public SportMapper(Database database) {
        this.database = database;
    }

    public int deleteSport(int sportId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM sport WHERE sport_id = ? AND sport_id NOT IN (SELECT sport_id FROM bmi_entry)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, sportId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public List<Sport> getAllSports() throws UserException {
        List<Sport> sportList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM sport";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("sport_id");
                    String name = rs.getString("name");
                    sportList.add(new Sport(id, name));
                }
                return sportList;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public Sport getSportById(int sportId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM sport WHERE sport_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, sportId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("sport_id");
                    String name = rs.getString("name");
                    return new Sport(id, name);
                }
                throw new UserException("Sportsgren findes ikke for sport_id = " + sportId);

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }



    public int updateSport(int sportId, String name) throws UserException {
        try (Connection connection = database.connect()) {

            String sql = "UPDATE sport SET name = ? WHERE sport_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1,name);
                ps.setInt(2, sportId);
                int rowsInserted = ps.executeUpdate();
                return rowsInserted;


            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}


