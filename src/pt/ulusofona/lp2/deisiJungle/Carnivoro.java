package pt.ulusofona.lp2.deisiJungle;

public class Carnivoro extends Type {
    private boolean toxicMeat;

    Carnivoro(String name){
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
