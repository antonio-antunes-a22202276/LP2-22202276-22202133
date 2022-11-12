package pt.ulusofona.lp2.deisiJungle;

public class Specie {
    String specieId;
    String specieName;

    Specie(String specieId, String specieName) {
        this.specieId = specieId;
        this.specieName = specieName;
    }

    String getSpecieId() {
        return specieId;
    }

    String getSpecieName() {
        return specieName;
    }
}
