package io.github.ausf_software.turing;

import java.util.List;

public class ResultTuring {
    String tape;
    int steps;
    int headPosition;
    List<String> history;

    public ResultTuring(String tape, int steps, int headPosition, List<String> history) {
        this.tape = tape;
        this.steps = steps;
        this.headPosition = headPosition;
        this.history = history;
    }
}
