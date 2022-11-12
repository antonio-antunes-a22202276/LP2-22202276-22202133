package pt.ulusofona.lp2.deisiJungle;

public class Player {
    String id;
    String name;
    Specie specie;
    Energy energy;
    Position position;

    Player(String id, String name, Specie specie, Energy energy, Position position) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.energy = energy;
        this.position = position;
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

    Energy getEnergy() {
        return energy;
    }

    Position getPosition() {
        return position;
    }
}