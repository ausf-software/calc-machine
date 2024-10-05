package io.github.ausf_software.markov;

public class Result {
    private Integer steps;
    private String res;

    public Result(Integer steps, String res) {
        this.steps = steps;
        this.res = res;
    }

    public Integer getSteps() {
        return steps;
    }

    public String getRes() {
        return res;
    }
}
