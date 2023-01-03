package pt.ulusofona.lp2.deisiJungle;

public class InvalidInitialJungleException extends Exception {
    private String msg;
    private boolean invalidPlayer;
    private boolean invalidFood;
    public InvalidInitialJungleException(String msg, String errorType) {
        this.msg = msg;
        invalidPlayer = false;
        invalidFood = false;
        if(errorType.equals("player")) {
            invalidPlayer = true;
        }
        if(errorType.equals("food")) {
            invalidFood = true;
        }
    }

    public String getMessage() {
        return msg;
    }

    public boolean isInvalidPlayer() {
        return invalidPlayer;
    }

    public boolean isInvalidFood() {
        return invalidFood;
    }
}
