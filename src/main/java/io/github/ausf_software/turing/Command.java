package io.github.ausf_software.turing;

public class Command {
    char symbolToWrite;
    MoveType move;
    String nextState;

    public Command(char symbolToWrite, MoveType move, String nextState) {
        this.symbolToWrite = symbolToWrite;
        this.move = move;
        this.nextState = nextState;
    }
}
