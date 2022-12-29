package pt.ulusofona.lp2.deisiJungle;

public class CachoBananas extends Food {
    private int bananasNr;

    public CachoBananas(String id, String position, String name, String fileName){
        super(id, position, name, fileName);
        this.bananasNr = 3;
    }

    @Override
    public int getNumber() {
        return bananasNr;
    }

    @Override
    public void updateNumber(){
        this.bananasNr -= 1;
    }
}
