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

    @Override
    public void eatFood(Specie specie, int roundNr) {
        int energy = 0;
        if(specie.getType().getName().equals("O")) {
            energy = (int) Math.round(Math.floor(Integer.parseInt(specie.getEnergy().getActual())*1.2));
        }
        if(specie.getType().getName().equals("C") || specie.getType().getName().equals("H")) {
            energy = Integer.parseInt(specie.getEnergy().getActual()) + 15;
        }
        specie.getEnergy().updateEnergyByFood(energy);
        specie.updateEatenFoodNr();
    }
}
