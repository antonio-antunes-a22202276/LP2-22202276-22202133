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

    @Override
    public void eatFood(Specie specie, int roundNr) {
        int energy = 0;
        if(bananasNr!=0) {
            if (!specie.getAteBanana()) {
                energy = Integer.parseInt(specie.getEnergy().getActual()) + 40;
                specie.updateAteBanana();
            } else {
                energy = Integer.parseInt(specie.getEnergy().getActual()) - 40;
            }
            updateNumber(); //food.updateBananasNumber()
            specie.getEnergy().updateEnergyByFood(energy);
            specie.updateGetFoodNr();
        }
    }
}
