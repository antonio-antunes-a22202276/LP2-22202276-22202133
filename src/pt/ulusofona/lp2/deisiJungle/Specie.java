package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public class Specie implements Serializable {
    String id;
    String name;
    String image;
    String energy;
    String energyConsume;
    String energyGain;
    String speed;
    Type type;
    boolean ateBanana = false;
    int foodNr = 0;

    Specie(String id, String name, String image, String energy, String energyConsume, String energyGain, String speed, Type type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.energy = energy;
        this.energyConsume = energyConsume;
        this.energyGain = energyGain;
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

    String getEnergy(){
        return energy;
    }

    String getEnergyConsume() {return energyConsume; }

    String getEnergyGain() {return energyGain; }

    String getSpeed(){
        return speed;
    }

    Type getType(){
        return type;
    }

    void updateEnergy(int nrSquares, boolean hasPositiveEnergy){
        if (hasPositiveEnergy) {
            if (nrSquares == 0) {
                energy = String.valueOf(Integer.parseInt(energy) + Integer.parseInt(energyGain));
                if (Integer.parseInt(energy) > 200) {
                    energy = "200";
                }
            } else {
                energy = String.valueOf(Integer.parseInt(energy) - (Math.abs(nrSquares) * Integer.parseInt(energyConsume)));
            }
        } else {
            energy = String.valueOf(nrSquares);
        }
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

    void updateEnergyByFood(Food food, int nrJogada){
        if(food.getId().equals("c") && type.canGetMeatStatus()) {
            if(type.getMeatStatus()) {
                System.out.println("e toxica");
                energy = String.valueOf(Integer.parseInt(energy)/2);
            } else {
                System.out.println("nao e toxica");
                energy = String.valueOf(Integer.parseInt(energy) + 50);
            }
        }
        if(type.getName().equals("O")){ //Omnivero
            if(food.getId().equals("e")){energy = String.valueOf(Integer.parseInt(energy) + 20);} //erva
            if(food.getId().equals("a")){energy = String.valueOf(Math.round(Math.floor(Integer.parseInt(energy)*1.2)));} //water
        }
        if(type.getName().equals("C")){ //Carnivero
            if(food.getId().equals("e")){energy = String.valueOf(Integer.parseInt(energy) - 20);} //erva
            if(food.getId().equals("a")){energy = String.valueOf(Integer.parseInt(energy) + 15);} //water
        }
        if(type.getName().equals("H")){ //Herbivoro
            if(food.getId().equals("e")){energy = String.valueOf(Integer.parseInt(energy) + 20);} //erva
            if(food.getId().equals("a")){energy = String.valueOf(Integer.parseInt(energy) + 15);} //water
        }
        if(food.getId().equals("b")){ //banana || Os casos da banana e cogumelos é igual para todos os animais
            if(food.getNumber()!=0) { //food.getBananasNumber()!=0
                if (!getAteBanana()) {
                    energy = String.valueOf(Integer.parseInt(energy) + 40);
                    updateAteBanana();
                } else {
                    energy = String.valueOf(Integer.parseInt(energy) - 40);
                }
                food.updateNumber(); //food.updateBananasNumber()
            }
        }
        if(food.getId().equals("m")){ //cogumelos
            String multiplier = "0."+food.getNumber(); //food.getMushroomNumber()
            if(nrJogada%2==0) {energy = String.valueOf(Integer.parseInt(energy) + Math.round(Math.floor(Integer.parseInt(energy)*Double.parseDouble(multiplier))));}
            else {energy = String.valueOf(Integer.parseInt(energy) - Math.round(Math.floor(Integer.parseInt(energy)*Double.parseDouble(multiplier))));}
        }
        updateGetFoodNr();
        if(Integer.parseInt(energy) > 200){energy = "200";}
        if(Integer.parseInt(energy) < 0){energy = "0";}
    }
}
