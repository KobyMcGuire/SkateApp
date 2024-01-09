package com.techelevator.dao;

import com.techelevator.model.Trick;
import java.util.List;

public interface TrickDao {
    public List<Trick> fetchAllTricks();

    public Trick fetchTrickById(int id);

    public List<Trick> fetchUnknownTricks();

    public List<Trick> fetchKnownTricks();

    public Trick createTrick(Trick trick);

    public Trick updateTrick(Trick trick);

    public int deleteTrick(int id);




}
