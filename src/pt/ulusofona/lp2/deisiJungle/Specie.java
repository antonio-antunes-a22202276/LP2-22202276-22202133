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

    void updateEnergyByFood(String foodId){
        if(specieType.equals("O")){
            if(foodId.equals("e")){
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 20);
            }
            if(foodId.equals("a")){

            }
        }
        if(specieType.equals("C")){
            if(foodId.equals("e")){
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) - 20);
            }
        }
        if(specieType.equals("H")){
            if(foodId.equals("e")){
                specieEnergy = String.valueOf(Integer.parseInt(specieEnergy) + 20);
            }
        }
        if(Integer.parseInt(specieEnergy) > 200){
            specieEnergy = "200";
        }
        if(Integer.parseInt(specieEnergy) < 0){
            specieEnergy = "0";
        }
    }
}
