package pt.ulusofona.lp2.deisiJungle;

public class Food {
    String id;
    String position;
    String name;
    String fileName;

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
}
