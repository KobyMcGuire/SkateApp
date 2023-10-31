package com.techelevator.model;

public class Trick {

    private String name;
    private String flipOrShuv;
    private String stance;
    private String known;

    public Trick(String name, String flipOrShuv, String stance, String known) {
        this.name = name;
        this.flipOrShuv = flipOrShuv;
        this.stance = stance;
        this.known = known;
    }

    public String getName() {
        return name;
    }

    public String getFlipOrShuv() {
        return flipOrShuv;
    }


    public String getStance() {
        return stance;
    }

    public String getKnown() {
        return known;
    }

    public void setKnown(String known) {
        this.known = known;
    }
}
