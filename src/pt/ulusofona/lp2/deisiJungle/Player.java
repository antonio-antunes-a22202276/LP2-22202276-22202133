package pt.ulusofona.lp2.deisiJungle;

public class Player {
    String id;
    String name;
    String specieId;
    int energy;
    int squareId;

    Player(String id, String name, String specieId, int energy, int squareId) {
        this.id = id;
        this.name = name;
        this.specieId = specieId;
        this.energy = energy;
        this.squareId = squareId;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getSpecieId() {
        return specieId;
    }

    int getEnergy() {
        return energy;
    }

    int getSquareId() {
        return squareId;
    }

    void updateEnergy() {
        energy -= 2;
    }

    void updateSquareId(int newSquareId) {
        squareId = newSquareId;
    }
}