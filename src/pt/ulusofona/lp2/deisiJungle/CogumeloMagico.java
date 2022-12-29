package pt.ulusofona.lp2.deisiJungle;

public class CogumeloMagico extends Food {
    private int mushroomNr;

    public CogumeloMagico(String id, String position, String name, String fileName){
        super(id, position, name, fileName);
        this.mushroomNr = (int)(Math.random() * (50-10+1)+10);
    }

    @Override
    public int getNumber() {
        return mushroomNr;
    }

    @Override
    public void updateNumber(){}
}
