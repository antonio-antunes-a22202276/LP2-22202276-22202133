package pt.ulusofona.lp2.deisiJungle;

public class Player {
    String id;
    String name;
    String specieId;
    int energy;
    int squareId;
    String specieName;

    Player(String id, String name, String specieId, int energy, int squareId, String specieName) {
        this.id = id;
        this.name = name;
        this.specieId = specieId;
        this.energy = energy;
        this.squareId = squareId;
        this.specieName = specieName;
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

    String getSpecieName(){
        return specieName;
    }
}