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
}
