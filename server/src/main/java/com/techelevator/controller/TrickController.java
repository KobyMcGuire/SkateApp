package com.techelevator.controller;

import com.techelevator.dao.TrickDao;
import com.techelevator.model.Trick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TrickController {

    @Autowired
    private TrickDao dao;

    // Fetch a list of all tricks
    @RequestMapping(path = "/tricks", method = RequestMethod.GET)
    public List<Trick> list() {
        List<Trick> tricks = new ArrayList<>();

        tricks = dao.fetchAllTricks();

        return tricks;
    }

    // Fetch a list of unknown tricks
    @RequestMapping(path = "/tricks/unknown", method = RequestMethod.GET)
    public List<Trick> listUnknownTricks() {
        List<Trick> tricks = new ArrayList<>();

        tricks = dao.fetchUnknownTricks();

        return tricks;
    }

    //Fetch a list of known tricks
    @RequestMapping(path = "/tricks/known", method = RequestMethod.GET)
    public List<Trick> listKnownTricks() {
        List<Trick> tricks = new ArrayList<>();

        tricks = dao.fetchKnownTricks();

        return tricks;
    }

    // Add a trick to the DB
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/tricks", method = RequestMethod.POST)
    public Trick create(@Valid @RequestBody Trick trick) {

        Trick addedTrick = dao.createTrick(trick);
        return addedTrick;

    }




}
