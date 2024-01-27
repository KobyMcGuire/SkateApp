package com.techelevator.dao;

import com.techelevator.model.Trick;
import java.util.List;

public interface TrickDao {
     List<Trick> fetchAllTricks();

     Trick fetchTrickById(int id);

     List<Trick> fetchUnknownTricks();

     List<Trick> fetchKnownTricks();

     List<Trick> fetchPracticeBagTricks(int id);

     Trick createTrick(Trick trick);

     Trick updateTrick(Trick trick);

     int deleteTrick(int id);




}
