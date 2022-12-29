package pt.ulusofona.lp2.deisiJungle;

public class Carne extends Food {
    public Carne(String id, String position, String name, String fileName){
        super(id, position, name, fileName);
    }

    @Override
    public int getNumber() {
        return -1;
    }

    @Override
    public void updateNumber(){}

    @Override
    public void eatFood(Specie specie, int roundNr) {
        int energy = 0;
        if(specie.getType().canGetMeatStatus()) {
            if(specie.getType().getMeatStatus()) {
                energy = Integer.parseInt(specie.getEnergy().getActual())/2;
            } else {
                energy = Integer.parseInt(specie.getEnergy().getActual()) + 50;
            }
            specie.getEnergy().updateEnergyByFood(energy);
            specie.updateGetFoodNr();
        }
    }
}
