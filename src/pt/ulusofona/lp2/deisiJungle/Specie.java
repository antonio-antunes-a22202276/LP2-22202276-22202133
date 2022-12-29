package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public class Specie implements Serializable {
    String id;
    String name;
    String image;
    Energy energy;
    String speed;
    Type type;
    boolean ateBanana = false;
    int foodNr = 0;

    Specie(String id, String name, String image, Energy energy, String speed, Type type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.energy = energy;
        this.speed = speed;
        this.type = type;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getImage() {return image; }

    Energy getEnergy(){
        return energy;
    }

    String getSpeed(){
        return speed;
    }

    Type getType(){
        return type;
    }

    boolean getAteBanana() {
        return ateBanana;
    }

    void updateAteBanana() {
        ateBanana = true;
    }

    int getFoodNr() {
        return foodNr;
    }

    void updateGetFoodNr() {
        foodNr += 1;
    }
}
