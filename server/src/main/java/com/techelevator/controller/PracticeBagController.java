package com.techelevator.controller;

import com.techelevator.dao.PracticeBagDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.PracticeBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class PracticeBagController {

    @Autowired
    private PracticeBagDao practiceBagDao;

    // Fetch all Practice Bags from DB
    @RequestMapping(path="/practicebags", method = RequestMethod.GET)
    public List<PracticeBag> fetchAllPracticeBags() {
        return practiceBagDao.fetchAllPracticeBags();
    }

}
