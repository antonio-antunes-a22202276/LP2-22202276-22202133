package pt.ulusofona.lp2.deisiJungle;

public class Mitologico extends Type{

    Mitologico(String name){
        super(name);
    }
    @Override
    public boolean canGetMeatStatus() {
        return false;
    }

    @Override
    public boolean getMeatStatus() {
        return false;
    }

    @Override
    public void updateMeatStatus() {

    }
}
