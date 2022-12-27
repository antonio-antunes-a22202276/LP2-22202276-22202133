package pt.ulusofona.lp2.deisiJungle;

public class Specie {
    String specieId;
    String specieName;
    String specieImage;
    String specieEnergy;
    String specieEnergyConsume;
    String specieEnergyGain;
    String specieSpeed;
    String specieType;
    int bananaNr = 0;
    boolean canEatBanana = true;
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

    int getBananaNr() {
        return bananaNr;
    }

    void updateBananaNr() {
        bananaNr += 1;
    }

    boolean getCanEatBanana() {
        return canEatBanana;
    }

    void updateCanEatBanana() {
        canEatBanana = false;
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
            if(food.getId().equals("e")){ //erva
                updateGetFoodNr();
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 20);
            }
            if(food.getId().equals("a")){ //water
                updateGetFoodNr();
                specieEnergy = String.valueOf(Math.round(Math.floor(Integer.parseInt(specieEnergy)*1.2)));
            }
            if(food.getId().equals("b") && getCanEatBanana()){ //banana
                if(getBananaNr()==0) {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 40);
                } else {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - 40);
                }
                updateBananaNr();
                updateGetFoodNr();
            }
            if(food.getId().equals("c")){ //carne
                updateGetFoodNr();
                if(!getCarneToxica()) {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 50);
                } else {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy)/2);
                }
            }
        }
        if(specieType.equals("C")){ //Carnivero
            if(food.getId().equals("e")){ //erva
                updateGetFoodNr();
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - 20);
            }
            if(food.getId().equals("a")){ //water
                updateGetFoodNr();
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 15);
            }
            if(food.getId().equals("b") && getCanEatBanana()){ //banana
                if(getBananaNr()==0) {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 40);
                } else {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - 40);
                }
                updateBananaNr();
                updateGetFoodNr();
            }
            if(food.getId().equals("c")){ //carne
                updateGetFoodNr();
                if(!getCarneToxica()) {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 50);
                } else {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy)/2);
                }
            }
        }
        if(specieType.equals("H")){ //Herbivoro
            if(food.getId().equals("e")){ //erva
                updateGetFoodNr();
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 20);
            }
            if(food.getId().equals("a")){ //water
                updateGetFoodNr();
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 15);
            }
            if(food.getId().equals("b") && getCanEatBanana()){ //banana
                if(getBananaNr()==0) {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 40);
                } else {
                    specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - 40);
                }
                updateBananaNr();
                updateGetFoodNr();
            }
        }
        if(food.getId().equals("m")){ //cogumelos
            String multiplier = "0."+food.getMushroomNumber();
            if(nrJogada%2==0) {
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + Math.round(Math.floor(Integer.parseInt(specieEnergy)*Double.parseDouble(multiplier))));
            } else {
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - Math.round(Math.floor(Integer.parseInt(specieEnergy)*Double.parseDouble(multiplier))));
            }
            updateGetFoodNr();
        }
        if(Integer.parseInt(specieEnergy) > 200){
            specieEnergy = "200";
        }
        if(Integer.parseInt(specieEnergy) < 0){
            specieEnergy = "0";
        }
    }
}
