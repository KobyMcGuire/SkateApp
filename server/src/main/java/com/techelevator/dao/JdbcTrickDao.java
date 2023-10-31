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
