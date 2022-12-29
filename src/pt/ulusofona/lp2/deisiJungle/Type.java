package pt.ulusofona.lp2.deisiJungle;

public abstract class Type {
    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canGetMeatStatus();
    public abstract boolean getMeatStatus();
    public abstract void updateMeatStatus();
}
