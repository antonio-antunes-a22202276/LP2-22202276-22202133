package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public abstract class Type implements Serializable {
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
