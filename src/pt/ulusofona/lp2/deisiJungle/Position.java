package pt.ulusofona.lp2.deisiJungle;

public class Position {
    int squareId;

    Position(int squareId) {
        this.squareId = squareId;
    }

    int getSquareId() {
        return squareId;
    }

    void updateSquareId(int newSquareId) {
        squareId = newSquareId;
    }
}
