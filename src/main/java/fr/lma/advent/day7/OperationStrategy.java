package fr.lma.advent.day7;

public interface OperationStrategy {

    /**
     * Computes the given operation and outputs a result.
     *
     * @param operation The operation to compute.
     * @return The output, never null.
     */
    OperationResult compute(Operation operation);

}
