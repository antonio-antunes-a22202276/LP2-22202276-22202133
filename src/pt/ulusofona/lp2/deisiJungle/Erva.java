package pt.ulusofona.lp2.deisiJungle;

public class Erva extends Food{
    private int consumedTimes;
    public Erva(String id, String position, String name, String fileName){
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
        if(specie.getType().getName().equals("O") || specie.getType().getName().equals("H")) {
            energy = Integer.parseInt(specie.getEnergy().getActual()) + 20;
        }
        if(specie.getType().getName().equals("C")) {
            energy = Integer.parseInt(specie.getEnergy().getActual()) - 20;
        }
        specie.getEnergy().updateEnergyByFood(energy);
        specie.updateEatenFoodNr();
        updateConsumedTimes();
    }
}
