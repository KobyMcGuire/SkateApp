package com.techelevator.controller;


import com.techelevator.dao.PlayerDAO;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerDAO playerdao;

    @RequestMapping(path = "/heartbeat", method = RequestMethod.GET)
    public String heartbeat() {
        return "beat";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/players", method = RequestMethod.GET)
    public List<Player> fetchAllPlayers() {
        List<Player> players = null;

        try {
            players = playerdao.fetchAllPLayers();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return players;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "/players/{id}", method = RequestMethod.GET)
    public Player fetchPlayerById(@PathVariable int id) {
        Player foundPlayer = playerdao.fetchPlayerByPlayerId(id);

        if (foundPlayer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return foundPlayer;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public Player addPlayer(@Valid @RequestBody Player player) {
        return playerdao.addPLayer(player);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/players", method = RequestMethod.PUT)
    public Player updatePlayer(@Valid @RequestBody Player player) {
      return playerdao.updatePlayer(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/players/{id}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable int id) {

        if (playerdao.fetchPlayerByPlayerId(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        playerdao.deletePlayer(id);
    }



}
