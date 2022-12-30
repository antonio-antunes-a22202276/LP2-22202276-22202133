package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public class Player implements Serializable {
    private String id;
    private String name;
    private Specie specie;
    private int squareNr;
    private int travelledDistance = 0;

    Player(String id, String name, Specie specie) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.squareNr = 1;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Specie getSpecie() {
        return specie;
    }

    public int getSquareNr() {
        return squareNr;
    }

    public void updateSquareNr(int newSquareId) {
        squareNr = newSquareId;
    }

    public void updateTravelledDistance(int nr) {
        travelledDistance += nr;
    }

    public int getTravelledDistance() {
        return travelledDistance;
    }
}