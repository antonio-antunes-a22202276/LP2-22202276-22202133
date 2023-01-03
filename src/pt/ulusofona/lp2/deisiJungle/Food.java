package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public abstract class Food implements Serializable {
    private String id;
    private String squareNr;
    private String name;
    private String fileName;

    Food(String id, String squareNr, String name, String fileName) {
        this.id = id;
        this.squareNr = squareNr;
        this.name = name;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public String getSquareNr() {
        return squareNr;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    public abstract int getNumber();

    public abstract void updateNumber();

    public abstract void eatFood(Specie specie, int roundNr);

    public abstract int getConsumedTimes();

    public abstract void updateConsumedTimes();
}
