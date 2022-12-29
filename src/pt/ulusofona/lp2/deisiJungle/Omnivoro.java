package pt.ulusofona.lp2.deisiJungle;

public class Omnivoro extends Type {
    private boolean toxicMeat;

    Omnivoro(String name){
        super(name);
        toxicMeat = false;
    }

    @Override
    public boolean canGetMeatStatus() {
        return true;
    }

    @Override
    public boolean getMeatStatus() {
        return toxicMeat;
    }

    @Override
    public void updateMeatStatus() {
        toxicMeat = true;
    }
}
