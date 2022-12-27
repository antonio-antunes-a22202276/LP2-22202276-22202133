package pt.ulusofona.lp2.deisiJungle;

public class Food {
    String id;
    String position;
    String name;
    String fileName;
    int mushroomNumber;

    Food(String id, String position, String name, String fileName) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.fileName = fileName;
    }

    Food(String id, String position, String name, String fileName, int mushroomNumber) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.fileName = fileName;
        this.mushroomNumber = mushroomNumber;
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
}
