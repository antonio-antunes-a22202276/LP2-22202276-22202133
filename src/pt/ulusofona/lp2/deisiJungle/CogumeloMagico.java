package pt.ulusofona.lp2.deisiJungle;

public class CogumeloMagico extends Food {
    private int mushroomNr;
    private int consumedTimes;

    public CogumeloMagico(String id, String position, String name, String fileName){
        super(id, position, name, fileName);
        this.mushroomNr = (int)(Math.random() * (50-10+1)+10);
        consumedTimes = 0;
    }

    @Override
    public int getNumber() {
        return mushroomNr;
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
        String multiplier = "0."+getNumber();
        if(roundNr%2==0) {
            energy = (int) (Integer.parseInt(specie.getEnergy().getActual()) + Math.round(Math.floor(Integer.parseInt(specie.getEnergy().getActual())*Double.parseDouble(multiplier))));
        } else {
            energy = (int) (Integer.parseInt(specie.getEnergy().getActual()) - Math.round(Math.floor(Integer.parseInt(specie.getEnergy().getActual())*Double.parseDouble(multiplier))));
        }
        specie.getEnergy().updateEnergyByFood(energy);
        specie.updateEatenFoodNr();
        updateConsumedTimes();
    }
}
