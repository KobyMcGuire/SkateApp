package com.techelevator.controller;

import com.techelevator.dao.TrickDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Trick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
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

    // Fetch trick by id
    @RequestMapping(path = "/tricks/{id}", method = RequestMethod.GET)
    public Trick fetchTrickById(@PathVariable int id) {
        Trick fetchedTrick = null;

        fetchedTrick = dao.fetchTrickById(id);
        if (fetchedTrick == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There was no trick found with the given id.");
        }

        return fetchedTrick;
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

    // Update a trick in the DB
    @RequestMapping(path =  "/tricks/{id}", method = RequestMethod.PUT)
    public Trick update(@Valid @RequestBody Trick trick, @PathVariable int id) {
        return dao.updateTrick(trick);
    }

    // Delete trick by id in the database
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/tricks/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        int affectedRows = dao.deleteTrick(id);

        if (affectedRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trick with the id given was found.");
        }
    }




}
