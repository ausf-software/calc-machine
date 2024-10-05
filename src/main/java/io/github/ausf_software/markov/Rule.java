package io.github.ausf_software.markov;

import java.util.regex.Pattern;

class Rule {
    private String pattern;
    private String replacement;
    private boolean stopped;

    public Rule(String pattern, String replacement, boolean stopped) {
        this.pattern = pattern;
        this.replacement = replacement;
        this.stopped = stopped;
    }

    public boolean isStopped() {
        return stopped;
    }

    public String getPattern() {
        return pattern;
    }

    public String getReplacement() {
        return replacement;
    }

    public static Rule parse(String str) {
        String[] parts;
        boolean stopped;

        if (str.contains("->")) {
            parts = str.split("->");
            stopped = false;
        } else if (str.contains(" => ")) {
            parts = str.split("=>");
            stopped = true;
        } else {
            throw new IllegalArgumentException("Неверный формат правила. Ожидается 'паттерн -> замена' или 'паттерн => замена'.");
        }

        if (parts.length != 2) {
            throw new IllegalArgumentException("Неверный формат правила. Ожидается два элемента: паттерн и замена.");
        }

        String pattern = parts[0].trim(); // Экранирование паттерна
        String replacement = parts[1].trim();

        return new Rule(pattern, replacement, stopped);
    }
}
