package com.techelevator.controller;

import com.techelevator.dao.TrickDao;
import com.techelevator.model.Trick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TrickController {

    @Autowired
    private TrickDao dao;

    @RequestMapping(path = "/tricks", method = RequestMethod.GET)
    public List<Trick> list() {
        List<Trick> tricks = new ArrayList<>();

        tricks = dao.fetchAllTricks();

        return tricks;
    }




}
