package pt.ulusofona.lp2.deisiJungle;

public class Carne extends Food {
    private int consumedTimes;
    public Carne(String id, String position, String name, String fileName){
        super(id, position, name, fileName);
        consumedTimes = 0;
    }

    @Override
    public int getNumber() {
        return -1;
    }

    @Override
    public int getConsumedTimes() {
        return consumedTimes;
    }

    @Override
    public void updateNumber(){}

    @Override
    public void updateConsumedTimes() {
        consumedTimes += 1;
    }

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
            specie.updateEatenFoodNr();
            updateConsumedTimes();
        }
    }
}
