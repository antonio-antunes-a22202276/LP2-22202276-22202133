package pt.ulusofona.lp2.deisiJungle;

public class Erva extends Food{
    public Erva(String id, String position, String name, String fileName){
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
        if(specie.getType().getName().equals("O") || specie.getType().getName().equals("H")) {
            energy = Integer.parseInt(specie.getEnergy().getActual()) + 20;
        }
        if(specie.getType().getName().equals("C")) {
            energy = Integer.parseInt(specie.getEnergy().getActual()) - 20;
        }
        specie.getEnergy().updateEnergyByFood(energy);
        specie.updateEatenFoodNr();
    }
}
