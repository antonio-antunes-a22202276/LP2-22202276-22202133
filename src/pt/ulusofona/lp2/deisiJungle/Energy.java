package pt.ulusofona.lp2.deisiJungle;

import java.io.Serializable;

public class Energy implements Serializable {
    private String actual;
    private String consume;
    private String gain;

    Energy(String actual, String consume, String gain) {
        this.actual = actual;
        this.consume = consume;
        this.gain = gain;
    }

    public String getActual() {
        return actual;
    }

    public String getConsume() {
        return consume;
    }

    public String getGain() {
        return gain;
    }

    void updateMovementEnergy(int nrSquares){
        if (nrSquares == 0) {
            actual = String.valueOf(Integer.parseInt(actual) + Integer.parseInt(gain));
            if (Integer.parseInt(actual) > 200) {
                actual = "200";
            }
        } else {
            actual = String.valueOf(Integer.parseInt(actual) - (Math.abs(nrSquares) * Integer.parseInt(consume)));
        }
    }

    void updateEnergyByFood(int energy) {
        actual = String.valueOf(energy);
        if (energy > 200) { actual = "200"; }
        if (energy < 0) { actual = "0"; }
    }
}
