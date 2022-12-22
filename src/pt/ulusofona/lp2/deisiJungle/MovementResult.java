package pt.ulusofona.lp2.deisiJungle;

public class MovementResult {
    MovementResultCode code;
    String message;


    MovementResult(MovementResultCode code) {
        this.code = code;
    }

    MovementResult(MovementResultCode code, String message) {
        this.message = message;
    }
}

