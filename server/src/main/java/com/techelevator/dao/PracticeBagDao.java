package com.techelevator.dao;

import java.util.*;
import com.techelevator.model.PracticeBag;

public interface PracticeBagDao {
    List<PracticeBag> fetchAllPracticeBags();

    PracticeBag fetchPracticeBagById();

    PracticeBag createPracticeBag();

    int deletePracticeBag();
}
