package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Trick {

    private int id;
    @NotBlank(message = "You can not leave the name field empty.")
    private String name;
    @NotBlank(message = "You can not leave the flip or shuv field empty.")
    private String flipOrShuv;
    @NotBlank(message = "You can not leave the stance field empty.")
    private String stance;
    @NotBlank(message = "You can not leave the known field empty.")
    private String known;

    public Trick() {}

    public Trick(int id, String name, String flipOrShuv, String stance, String known) {
        this.id = id;
        this.name = name;
        this.flipOrShuv = flipOrShuv;
        this.stance = stance;
        this.known = known;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlipOrShuv() {
        return flipOrShuv;
    }

    public void setFlipOrShuv(String flipOrShuv) {
        this.flipOrShuv = flipOrShuv;
    }

    public String getStance() {
        return stance;
    }

    public void setStance(String stance) {
        this.stance = stance;
    }

    public String getKnown() {
        return known;
    }

    public void setKnown(String known) {
        this.known = known;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
