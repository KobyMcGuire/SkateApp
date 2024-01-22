package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.PracticeBag;
import com.techelevator.model.Trick;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JdbcPracticeBagDao implements PracticeBagDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPracticeBagDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<PracticeBag> fetchAllPracticeBags() {
        List<PracticeBag> practiceBags = new ArrayList<>();
        String sql = "SELECT * from practice_bags ORDER BY practice_bags.practice_bag_id DESC";

        try {
            SqlRowSet results =  jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                practiceBags.add(mapRowToPracticeBag(results));
            }
        }
        catch (Exception e) {
            throw new DaoException("There was an error fetching all practice bags");
        }

        return practiceBags;
    }

    @Override
    public PracticeBag fetchPracticeBagById() {
        return null;
    }

    @Override
    public PracticeBag createPracticeBag() {
        return null;
    }

    @Override
    public int deletePracticeBag() {
        return 0;
    }

    private PracticeBag mapRowToPracticeBag(SqlRowSet row) {
        PracticeBag practiceBag = new PracticeBag();
        practiceBag.setId(row.getInt("practice_bag_id"));
        practiceBag.setDate(row.getString("bag_creation_date"));
        return practiceBag;
    }
}
