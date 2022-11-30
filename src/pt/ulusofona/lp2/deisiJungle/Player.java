package pt.ulusofona.lp2.deisiJungle;

public class Player {
    String id;
    String name;
    Specie specie;
    int squareId;

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
}