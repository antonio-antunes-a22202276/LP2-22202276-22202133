package pt.ulusofona.lp2.deisiJungle;

public class Specie {
    String specieId;
    String specieName;
    String specieSpeed;
    String specieEnergy;

    Specie(String specieId, String specieName, String specieSpeed, String specieEnergy) {
        this.specieId = specieId;
        this.specieName = specieName;
        this.specieSpeed = specieSpeed;
        this.specieEnergy = specieEnergy;
    }

    String getSpecieId() {
        return specieId;
    }

    String getSpecieName() {
        return specieName;
    }

    String getSpecieSpeed(){
        return specieSpeed;
    }

    String getSpecieEnergy(){
        return specieEnergy;
    }

    String updateEnergy(){
        //TODO
        return "";
    }
}
