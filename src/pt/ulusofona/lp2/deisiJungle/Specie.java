package pt.ulusofona.lp2.deisiJungle;

public class Specie {
    String specieId;
    String specieName;
    String specieImage;
    String specieEnergy;
    String specieEnergyConsume;
    String specieEnergyGain;
    String specieSpeed;

    Specie(String specieId, String specieName, String specieImage, String specieEnergy, String specieEnergyConsume, String specieEnergyGain, String specieSpeed) {
        this.specieId = specieId;
        this.specieName = specieName;
        this.specieImage = specieImage;
        this.specieEnergy = specieEnergy;
        this.specieEnergyConsume = specieEnergyConsume;
        this.specieEnergyGain = specieEnergyGain;
        this.specieSpeed = specieSpeed;
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


    String updateEnergy(){
        //TODO
        return "";
    }
}
