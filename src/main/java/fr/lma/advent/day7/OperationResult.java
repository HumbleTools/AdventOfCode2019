package fr.lma.advent.day7;

public class OperationResult {

    private Integer output;
    private boolean isIndex;

    public OperationResult(final boolean isIndex, final Integer output) {
        this.output = output;
        this.isIndex = isIndex;
    }

    public OperationResult() {
        // default
    }

    public OperationResult(final boolean isIndex) {
        this.isIndex = isIndex;
    }

    public Integer getOutput() {
        return output;
    }

    public void setOutput(final Integer output) {
        this.output = output;
    }

    public boolean isIndex() {
        return isIndex;
    }

    public void setIndex(final boolean index) {
        isIndex = index;
    }
}
