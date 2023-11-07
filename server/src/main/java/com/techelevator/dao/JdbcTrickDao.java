package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Trick;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTrickDao implements TrickDao {

    // Using constructor dependency injection to create the jdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public JdbcTrickDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    // CRUD Methods
    public List<Trick> fetchAllTricks() {
        List<Trick> tricks = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM tricks";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                tricks.add(mapRowToTrick(results));
            }
        }
        catch (Exception e) {
            throw new DaoException("There was a problem fetching all of the tricks",e);
        }

        return tricks;
    }

    public Trick fetchTrickById(int id) {
        String sql = "SELECT * FROM tricks WHERE trick_id = ?";
        Trick fetchedTrick = null;

        try {
            SqlRowSet result =  jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()) {
                fetchedTrick = mapRowToTrick(result);
            }
        }
        catch(Exception e) {
            throw new DaoException("There was an issue with fetching the trick by the id given.", e);
        }

        return fetchedTrick;
    }


    public List<Trick> fetchKnownTricks() {
        List<Trick> tricks = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM tricks " +
                "WHERE known = 'Yes'";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                tricks.add(mapRowToTrick(results));
            }
        }
        catch (Exception e) {
            throw new DaoException("There was a problem fetching a list of known tricks");
        }

        return tricks;
    }

    public List<Trick> fetchUnknownTricks() {
        List<Trick> tricks = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM tricks " +
                     "WHERE known = 'No'";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                tricks.add(mapRowToTrick(results));
            }
        }
        catch (Exception e) {
            throw new DaoException("There was a problem fetching a list of unknown tricks", e);
        }

        return tricks;
    }

    public Trick createTrick(Trick trick) {

        String sql = "INSERT INTO tricks(name, flip_or_shuv, stance, known) " +
                     "VALUES(? ,?, ?, ?) " +
                     "RETURNING trick_id";

        try {
            int id = jdbcTemplate.queryForObject(sql, int.class, trick.getName(), trick.getFlipOrShuv(), trick.getStance(), trick.getKnown());
            trick.setId(id);
        }
        catch (Exception e) {
            throw new DaoException("There was an issue with adding the trick", e);
        }

        return trick;
    }

    public int deleteTrick(int id) {
        String sql = "DELETE FROM tricks WHERE trick_id = ?";
        int affectedRows;

        try {
            affectedRows = jdbcTemplate.update(sql, id);
        }
        catch (Exception e) {
            throw new DaoException("There was an error deleting the trick.", e);
        }

        return affectedRows;
    }

    private Trick mapRowToTrick(SqlRowSet row) {
        Trick trick = new Trick();
        trick.setId(row.getInt("trick_id"));
        trick.setName(row.getString("name"));
        trick.setStance(row.getString("stance"));
        trick.setFlipOrShuv(row.getString("flip_or_shuv"));
        trick.setKnown(row.getString("known"));
        return trick;
    }

}
