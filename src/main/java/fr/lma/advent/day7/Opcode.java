package fr.lma.advent.day7;

public enum Opcode {

    /**
     * Sums the first two parameters and stores the result at the index shown by the third parameter.
     */
    SUM(1, 3, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        final Integer paramValue1 = getParamValueFromMode(1, operation);
        final Integer result = paramValue0 + paramValue1;
        operation.getFullInput()[operation.getParams()[2]] = result;
        return null;
    }),

    /**
     * Multiplies the first two parameters and stores the result at the index shown by the third parameter.
     */
    MULTIPLY(2, 3, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        final Integer paramValue1 = getParamValueFromMode(1, operation);
        final Integer result = paramValue0 * paramValue1;
        operation.getFullInput()[operation.getParams()[2]] = result;
        return null;
    }),

    /**
     * Gets user input and stores it at the index shown by the first parameter. At the the moment, input is faked.
     */
    USER_INPUT(3, 1, (operation) -> {
        final Integer param0 = operation.getParams()[0];
        operation.getFullInput()[param0] = Advent_7.fakeUserInput;
        return null;
    }),

    /**
     * Outputs the value of the first parameter.
     */
    OUTPUT(4, 1, (operation) -> {
        return new OperationResult(false, getParamValueFromMode(0, operation));
    }),

    /**
     * If the first parameter is non-zero, it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
     */
    JUMP_IF_TRUE(5, 2, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        final OperationResult result = new OperationResult(true);
        if (0 != paramValue0) {
            result.setOutput(getParamValueFromMode(1, operation));
        }
        return result;
    }),

    /**
     * If the first parameter is zero, it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
     */
    JUMP_IF_FALSE(6, 2, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        final OperationResult result = new OperationResult(true);
        if (0 == paramValue0) {
            result.setOutput(getParamValueFromMode(1, operation));
        }
        return result;
    }),

    /**
     * If the first parameter is less than the second parameter, it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
     */
    IS_LESS_THAN(7, 3, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        final Integer paramValue1 = getParamValueFromMode(1, operation);
        final Integer paramValue2 = operation.getParams()[2];
        if (paramValue0 < paramValue1) {
            operation.getFullInput()[paramValue2] = 1;
        } else {
            operation.getFullInput()[paramValue2] = 0;
        }
        return new OperationResult();
    }),

    /**
     * If the first parameter is equal to the second parameter, it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
     */
    IS_EQUALS(8, 3, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        final Integer paramValue1 = getParamValueFromMode(1, operation);
        final Integer paramValue2 = operation.getParams()[2];
        if (paramValue0.equals(paramValue1)) {
            operation.getFullInput()[paramValue2] = 1;
        } else {
            operation.getFullInput()[paramValue2] = 0;
        }
        return new OperationResult();
    }),

    /**
     * If encountered, it stops the main program.
     */
    STOP(99, 0, (operation) -> {
        throw new UnsupportedOperationException("Halt.");
    });

    private OperationStrategy strategy;
    private Integer pureOpcode;
    private Integer paramCount;

    Opcode(final int pureOpcode, final int paramCount, final OperationStrategy strategy) {
        this.pureOpcode = pureOpcode;
        this.paramCount = paramCount;
        this.strategy = strategy;
    }

    public static Opcode resolve(final Integer value) {
        Opcode result = null;
        for (final Opcode opcode : Opcode.values()) {
            if (value.equals(opcode.pureOpcode)) {
                result = opcode;
                break;
            }
        }
        if (null == result) {
            throw new UnsupportedOperationException("Unknown opcode.");
        }
        return result;
    }

    public OperationResult compute(final Operation operation) {
        return strategy.compute(operation);
    }

    private static Integer getParamValueFromMode(final Integer paramIndex, final Operation operation) {
        final Operation.Mode paramMode = operation.getParamModes()[paramIndex];
        final Integer paramValue = operation.getParams()[paramIndex];
        if (paramMode == Operation.Mode.IMMEDIATE) {
            return paramValue;
        } else if (paramMode == Operation.Mode.POSITION) {
            return operation.getFullInput()[paramValue];
        } else {
            throw new UnsupportedOperationException(String.format("Mode %s inconnu !", paramMode));
        }
    }

    public OperationStrategy getStrategy() {
        return strategy;
    }

    public Integer getPureOpcode() {
        return pureOpcode;
    }

    public Integer getParamCount() {
        return paramCount;
    }
}
