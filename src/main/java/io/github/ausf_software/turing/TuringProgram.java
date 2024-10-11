package io.github.ausf_software.turing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuringProgram {
    String initialState;
    String tape;
    char emptySymbol;
    Map<String, List<Command>> transitionTable;

    public TuringProgram(String initialState, String tape, char emptySymbol) {
        this.initialState = initialState;
        this.tape = tape;
        this.emptySymbol = emptySymbol;
        this.transitionTable = new HashMap<>();
    }

    public void addTransition(String state, Command command) {
        transitionTable.putIfAbsent(state, new ArrayList<>());
        transitionTable.get(state).add(command);
    }
}
