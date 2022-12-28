package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public class Player implements Serializable {
    String id;
    String name;
    Specie specie;
    int squareId;
    int houseNr = 0;

    Player(String id, String name, Specie specie, int squareId) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.squareId = squareId;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    Specie getSpecie() {
        return specie;
    }

    int getSquareId() {
        return squareId;
    }

    void updateSquareId(int newSquareId) {
        squareId = newSquareId;
    }

    void updateHouseNr(int nr) {
        houseNr += nr;
    }

    int getHouseNr() {
        return houseNr;
    }
}