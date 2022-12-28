package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public class Specie implements Serializable {
    String specieId;
    String specieName;
    String specieImage;
    String specieEnergy;
    String specieEnergyConsume;
    String specieEnergyGain;
    String specieSpeed;
    String specieType;
    boolean ateBanana = false;
    boolean carneToxica = false;
    int foodNr = 0;

    Specie(String specieId, String specieName, String specieImage, String specieEnergy, String specieEnergyConsume, String specieEnergyGain, String specieSpeed, String specieType) {
        this.specieId = specieId;
        this.specieName = specieName;
        this.specieImage = specieImage;
        this.specieEnergy = specieEnergy;
        this.specieEnergyConsume = specieEnergyConsume;
        this.specieEnergyGain = specieEnergyGain;
        this.specieSpeed = specieSpeed;
        this.specieType = specieType;
    }

    String getSpecieId() {
        return specieId;
    }

    String getSpecieName() {
        return specieName;
    }

    String getSpecieImage() {return specieImage; }

    String getSpecieEnergy(){
        return specieEnergy;
    }

    String getSpecieEnergyConsume() {return specieEnergyConsume; }

    String getSpecieEnergyGain() {return specieEnergyGain; }

    String getSpecieSpeed(){
        return specieSpeed;
    }

    String getSpecieType(){
        return specieType;
    }

    void updateEnergy(int nrSquares, boolean hasPositiveEnergy){
        if (hasPositiveEnergy) {
            if (nrSquares == 0) {
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + Integer.parseInt(specieEnergyGain));
                if (Integer.parseInt(specieEnergy) > 200) {
                    specieEnergy = "200";
                }
            } else {
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - (Math.abs(nrSquares) * Integer.parseInt(specieEnergyConsume)));
            }
        } else {
            specieEnergy = String.valueOf(nrSquares);
        }
    }

    boolean getAteBanana() {
        return ateBanana;
    }

    void updateAteBanana() {
        ateBanana = true;
    }

    boolean getCarneToxica() {
        return carneToxica;
    }

    void updateCarneToxica() {
        carneToxica = true;
    }

    int getFoodNr() {
        return foodNr;
    }

    void updateGetFoodNr() {
        foodNr += 1;
    }

    void updateEnergyByFood(Food food, int nrJogada){
        if(specieType.equals("O")){ //Omnivero
            if(food.getId().equals("e")){specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 20);} //erva
            if(food.getId().equals("a")){specieEnergy = String.valueOf(Math.round(Math.floor(Integer.parseInt(specieEnergy)*1.2)));} //water
            if(food.getId().equals("c")){ //carne
                if(!getCarneToxica()) {specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 50);}
                else {specieEnergy = String.valueOf(Integer.parseInt(specieEnergy)/2);}
            }
        }
        if(specieType.equals("C")){ //Carnivero
            if(food.getId().equals("e")){specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - 20);} //erva
            if(food.getId().equals("a")){specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 15);} //water
            if(food.getId().equals("c")){//carne
                if(!getCarneToxica()) {specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 50);}
                else {specieEnergy = String.valueOf(Integer.parseInt(specieEnergy)/2);}
            }
        }
        if(specieType.equals("H")){ //Herbivoro
            if(food.getId().equals("e")){specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 20);} //erva
            if(food.getId().equals("a")){specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 15);} //water
        }
        if(food.getId().equals("b")){ //banana || Os casos da banana e cogumelos é igual para todos os animais
            if(food.getBananasNumber()!=0) {
                if (!getAteBanana()) {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 40);
                    updateAteBanana();
                } else {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - 40);
                }
                food.updateBananasNumber();
            }
        }
        if(food.getId().equals("m")){ //cogumelos
            String multiplier = "0."+food.getMushroomNumber();
            if(nrJogada%2==0) {specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + Math.round(Math.floor(Integer.parseInt(specieEnergy)*Double.parseDouble(multiplier))));}
            else {specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - Math.round(Math.floor(Integer.parseInt(specieEnergy)*Double.parseDouble(multiplier))));}
        }
        updateGetFoodNr();
        if(Integer.parseInt(specieEnergy) > 200){specieEnergy = "200";}
        if(Integer.parseInt(specieEnergy) < 0){specieEnergy = "0";}
    }
}
