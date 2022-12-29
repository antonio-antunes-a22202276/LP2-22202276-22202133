package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public class Specie implements Serializable {
    private String id;
    private String name;
    private String image;
    private Energy energy;
    private String speed;
    private Type type;
    private boolean ateBanana = false;
    private int foodNr = 0;

    Specie(String id, String name, String image, Energy energy, String speed, Type type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.energy = energy;
        this.speed = speed;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {return image; }

    public Energy getEnergy(){
        return energy;
    }

    public String getSpeed(){
        return speed;
    }

    public Type getType(){
        return type;
    }

    public boolean getAteBanana() {
        return ateBanana;
    }

    public void updateAteBanana() {
        ateBanana = true;
    }

    public int getFoodNr() {
        return foodNr;
    }

    public void updateGetFoodNr() {
        foodNr += 1;
    }
}
