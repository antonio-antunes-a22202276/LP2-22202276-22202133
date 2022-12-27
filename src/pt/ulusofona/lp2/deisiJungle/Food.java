package pt.ulusofona.lp2.deisiJungle;

public class Food {
    String id;
    String position;
    String name;
    String fileName;
    int mushroomNumber;
    int bananasNumber;

    Food(String id, String position, String name, String fileName) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    public int getMushroomNumber() {
        return mushroomNumber;
    }

    public void setMushroomNumber(int mushroomNumber) {
        this.mushroomNumber = mushroomNumber;
    }

    public int getBananasNumber() {
        return bananasNumber;
    }

    public void setBananasNumber(int bananasNumber) {
        this.bananasNumber = bananasNumber;
    }

    public void updateBananasNumber() {
        this.bananasNumber -= 1;
    }
}
