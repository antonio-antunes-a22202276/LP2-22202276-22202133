package pt.ulusofona.lp2.deisiJungle;

public class Energy {
    int energyQty;

    Energy(int energy) {
        this.energyQty = energy;
    }

    int getEnergyQty() {
        return energyQty;
    }

    void updateEnergy() {
        energyQty -= 2;
    }
}
