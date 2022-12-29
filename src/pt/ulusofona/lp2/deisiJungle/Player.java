package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public class Player implements Serializable {
    private String id;
    private String name;
    private Specie specie;
    private int squareId;
    private int houseNr = 0;

    Player(String id, String name, Specie specie, int squareId) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.squareId = squareId;
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

    public int getSquareId() {
        return squareId;
    }

    public void updateSquareId(int newSquareId) {
        squareId = newSquareId;
    }

    public void updateHouseNr(int nr) {
        houseNr += nr;
    }

    public int getHouseNr() {
        return houseNr;
    }
}