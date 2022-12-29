package pt.ulusofona.lp2.deisiJungle;

public class Agua extends Food{
    public Agua(String id, String position, String name, String fileName){
        super(id, position, name, fileName);
    }

    @Override
    public int getNumber() {
        return -1;
    }

    @Override
    public void updateNumber(){}
}
