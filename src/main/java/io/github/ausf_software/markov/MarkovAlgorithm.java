package io.github.ausf_software.markov;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MarkovAlgorithm {
    private List<Rule> rules;
    private String initialState;

    public MarkovAlgorithm(String initialState) {
        this.initialState = initialState;
        this.rules = new ArrayList<>();
    }

    public void addRule(String str) {
        if (str == null || str.isEmpty()) return;
        rules.add(Rule.parse(str));
    }

    public Result execute(int maxSteps) {
        String output = initialState;
        boolean ruleApplied;
        int steps = 0;
        int oldSteps;

        do {
            oldSteps = steps;
            ruleApplied = false;
            for (Rule rule : rules) {
                if (rule.getPattern().isEmpty()) {
                    output = rule.getReplacement() + output;
                    ruleApplied = true;
                    steps++;
                } else {
                    if (output.contains(rule.getPattern())) {
                        output = output.replaceAll(Pattern.quote(rule.getPattern()),
                                rule.getReplacement());
                        ruleApplied = true;
                        steps++;
                    }
                }
                if (oldSteps != steps) {
                    if(rule.isStopped())
                        ruleApplied = false;
                    break;
                }
            }
            if (maxSteps == steps)
                break;
        } while (ruleApplied);
        return new Result(steps, output);
    }

    public static void main(String[] args) {
        MarkovAlgorithm algorithm = new MarkovAlgorithm("aabbccddeeff");
        algorithm.addRule("cde -> a");
        algorithm.addRule("*b -> bb*");
        algorithm.addRule("*c -> cc*");
        algorithm.addRule("*d -> dd*");
        algorithm.addRule("*f -> ff*");
        algorithm.addRule("*a -> a*");
        algorithm.addRule("*e -> e*");
        algorithm.addRule("* => ");
        algorithm.addRule(" -> *");

        int maxSteps = 100; // Ограничение на количество шагов
        algorithm.execute(maxSteps);
    }
}
