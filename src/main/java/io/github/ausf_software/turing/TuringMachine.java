package io.github.ausf_software.turing;

import java.util.*;

public class TuringMachine {
    private Map<String, List<Command>> transitionTable = new HashMap<>();
    private String currentState;
    private String tape;
    private int headPosition;
    private char emptySymbol;
    private List<String> history;

    public TuringMachine(TuringProgram program) {
        this.currentState = program.initialState;
        this.tape = program.tape;
        this.headPosition = 0;
        this.emptySymbol = program.emptySymbol;
        this.history = new ArrayList<>();
        this.transitionTable = program.transitionTable;
    }

    private void moveCaret(MoveType type) {
        switch (type) {
            case LEFT:
                headPosition = headPosition - 1;
                if (headPosition < 0) {
                    tape = emptySymbol + tape; // Добавляем символ слева
                }
                headPosition = 0;
                break;
            case RIGHT:
                headPosition++;
                if (headPosition >= tape.length()) {
                    tape += emptySymbol; // Добавляем символ справа
                }
                break;
            case STAY:
                // Ничего не делаем
                break;
        }
    }

    private void commandExecute(Command command) {
        history.add(currentState + " | " + tape.charAt(headPosition));
        // Запись символа
        tape = tape.substring(0, headPosition) + command.symbolToWrite + tape.substring(headPosition + 1);
        // Перемещение каретки
        moveCaret(command.move);
        // Переход в новое состояние
        currentState = command.nextState;
    }

    private Optional<Command> getCurrentCommand(char currentSymbol) {
        List<Command> commands = transitionTable.get(currentState);
        Optional<Command> commandOpt = commands.stream()
                .filter(cmd -> cmd.symbolToWrite == currentSymbol)
                .findFirst();
        return commandOpt;
    }

    public ResultTuring run(int maxSteps) {
        for (int step = 0; step < maxSteps; step++) {
            if (!transitionTable.containsKey(currentState)) {
                break;
            }
            char currentSymbol = tape.charAt(headPosition);
            Optional<Command> commandOpt = getCurrentCommand(currentSymbol);

            if (commandOpt.isPresent()) {
                commandExecute(commandOpt.get());
            } else {
                break; // Нет подходящей команды
            }
        }
        return new ResultTuring(tape, history.size(), headPosition, history);
    }

    public void step() {
        if (!transitionTable.containsKey(currentState)) {
            return;
        }
        char currentSymbol = tape.charAt(headPosition);
        Optional<Command> commandOpt = getCurrentCommand(currentSymbol);

        if (commandOpt.isPresent()) {
            commandExecute(commandOpt.get());
        }
    }
}

