import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Advent_5 {

    /**
     * The above example program uses an input instruction to ask for a single number.
     * The program will then output 999 if the input value is below 8, output 1000 if
     * the input value is equal to 8, or output 1001 if the input value is greater than 8.
     */
    private static final String test_input = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
            "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
            "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";

    /**
     * Considers whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    private static final String test_input2 = "3,9,8,9,10,9,4,9,99,-1,8";

    private static final String input = "3,225,1,225,6,6,1100,1,238,225,104,0,1001,152" +
            ",55,224,1001,224,-68,224,4,224,1002,223,8,223,1001,224,4,224,1,224,223,223" +
            ",1101,62,41,225,1101,83,71,225,102,59,147,224,101,-944,224,224,4,224,1002," +
            "223,8,223,101,3,224,224,1,224,223,223,2,40,139,224,1001,224,-3905,224,4,224," +
            "1002,223,8,223,101,7,224,224,1,223,224,223,1101,6,94,224,101,-100,224,224,4,224," +
            "1002,223,8,223,101,6,224,224,1,224,223,223,1102,75,30,225,1102,70,44,224,101,-308" +
            "0,224,224,4,224,1002,223,8,223,1001,224,4,224,1,223,224,223,1101,55,20,225,1102,5" +
            "5,16,225,1102,13,94,225,1102,16,55,225,1102,13,13,225,1,109,143,224,101,-88,224,2" +
            "24,4,224,1002,223,8,223,1001,224,2,224,1,223,224,223,1002,136,57,224,101,-1140,22" +
            "4,224,4,224,1002,223,8,223,101,6,224,224,1,223,224,223,101,76,35,224,1001,224,-138" +
            ",224,4,224,1002,223,8,223,101,5,224,224,1,223,224,223,4,223,99,0,0,0,677,0,0,0,0,0" +
            ",0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105," +
            "1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,9999" +
            "9,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,11" +
            "05,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677,677,224,1002,223,2,2" +
            "23,1006,224,329,1001,223,1,223,8,677,226,224,102,2,223,223,1006,224,344,101,1,223,223,1107" +
            ",226,226,224,1002,223,2,223,1006,224,359,1001,223,1,223,1108,677,226,224,102,2,223," +
            "223,1005,224,374,1001,223,1,223,1007,226,226,224,102,2,223,223,1006,224,389,1001,223" +
            ",1,223,108,677,677,224,1002,223,2,223,1005,224,404,1001,223,1,223,1007,677,677,224" +
            ",102,2,223,223,1005,224,419,1001,223,1,223,8,226,677,224,102,2,223,223,1005,224,434" +
            ",101,1,223,223,1008,677,226,224,102,2,223,223,1006,224,449,1001,223,1,223,7,677,677" +
            ",224,102,2,223,223,1006,224,464,1001,223,1,223,8,226,226,224,1002,223,2,223,1005,22" +
            "4,479,1001,223,1,223,7,226,677,224,102,2,223,223,1006,224,494,1001,223,1,223,7,677,2" +
            "26,224,1002,223,2,223,1005,224,509,101,1,223,223,107,677,677,224,102,2,223,223,1006," +
            "224,524,101,1,223,223,1007,677,226,224,102,2,223,223,1006,224,539,101,1,223,223,107,2" +
            "26,226,224,1002,223,2,223,1006,224,554,101,1,223,223,1008,226,226,224,102,2,223,223,1" +
            "006,224,569,1001,223,1,223,1107,677,226,224,1002,223,2,223,1005,224,584,101,1,223,223" +
            ",1107,226,677,224,102,2,223,223,1005,224,599,101,1,223,223,1108,226,677,224,102,2,223,223," +
            "1005,224,614,101,1,223,223,108,677,226,224,102,2,223,223,1005,224,629,101,1,223,223,10" +
            "7,226,677,224,102,2,223,223,1006,224,644,1001,223,1,223,1108,226,226,224,1002,223,2,223" +
            ",1006,224,659,101,1,223,223,108,226,226,224,102,2,223,223,1005,224,674,101,1,223,223,4,223,99,226";

    public static Integer fakeUserInput = 1;

    public static void main(final String... args) {
        // Part 1
        compute(ArrayUtils.clone(getInput()));

        // Part 2
        fakeUserInput = 5;
        compute(ArrayUtils.clone(getInput()));
    }

    static void compute(final Integer[] intInput) {
        Operation currentOperation = new Operation(0, intInput);
        try {
            for (int index = 0; index < intInput.length; ) {
                currentOperation = new Operation(index, intInput);
                final Integer newIndex = currentOperation.compute();
                if (null == newIndex) {
                    index += currentOperation.getSize();
                } else {
                    index = newIndex;
                }
            }
        } catch (final UnsupportedOperationException uoe) {
            System.out.println(uoe.getMessage());
        }
    }

    static Integer[] getInput(final Integer noun, final Integer verb) {
        final Integer[] intInput = Arrays.stream(input.split(","))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        if (null != noun) {
            intInput[1] = noun;
        }
        if (null != verb) {
            intInput[2] = verb;
        }
        return intInput;
    }

    static Integer[] getInput() {
        return getInput(null, null);
    }

}

class Operation {

    public enum Mode {
        IMMEDIATE("1"),
        POSITION("0");

        private final String code;

        Mode(final String code) {
            this.code = code;
        }

        public static Mode resolve(final String value) {
            Mode result = null;
            for (final Mode mode : Mode.values()) {
                if (mode.code.equals(value)) {
                    result = mode;
                    break;
                }
            }
            return result;
        }
    }

    private Opcode opcode;
    private Integer fullOpcode;
    private String fullStringOpcode;
    private Integer size;
    private Integer[] params;
    private Mode[] paramModes;
    private Integer[] fullInput;
    private Integer opcodeIndex;

    public Operation(final Integer opcodeIndex, final Integer[] fullInput) {
        this.opcodeIndex = opcodeIndex;
        this.fullInput = fullInput;
        fullOpcode = fullInput[opcodeIndex];
        fullStringOpcode = String.format("%1$05d", fullOpcode);
        opcode = Opcode.resolve(Integer.valueOf(fullStringOpcode.substring(3)));
        size = opcode.getParamCount() + 1;
        if (1 == size) {
            this.params = new Integer[]{};
            this.paramModes = new Mode[]{};
        } else {
            this.params = Arrays.copyOfRange(fullInput, opcodeIndex + 1, opcodeIndex + size);
            this.paramModes = Arrays.stream(fullStringOpcode.substring(0, 3).split("d*?"))
                    .map(Mode::resolve)
                    .toArray(Mode[]::new);
            ArrayUtils.reverse(this.paramModes);
        }
    }

    public Integer compute() {
        return opcode.compute(this);
    }

    public Opcode getOpcode() {
        return opcode;
    }

    public void setOpcode(final Opcode opcode) {
        this.opcode = opcode;
    }

    public Integer getFullOpcode() {
        return fullOpcode;
    }

    public void setFullOpcode(final Integer fullOpcode) {
        this.fullOpcode = fullOpcode;
    }

    public String getFullStringOpcode() {
        return fullStringOpcode;
    }

    public void setFullStringOpcode(final String fullStringOpcode) {
        this.fullStringOpcode = fullStringOpcode;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(final Integer size) {
        this.size = size;
    }

    public Integer[] getParams() {
        return params;
    }

    public void setParams(final Integer[] params) {
        this.params = params;
    }

    public Mode[] getParamModes() {
        return paramModes;
    }

    public void setParamModes(final Mode[] paramModes) {
        this.paramModes = paramModes;
    }

    public Integer[] getFullInput() {
        return fullInput;
    }

    public void setFullInput(final Integer[] fullInput) {
        this.fullInput = fullInput;
    }

    public Integer getOpcodeIndex() {
        return opcodeIndex;
    }

    public void setOpcodeIndex(final Integer opcodeIndex) {
        this.opcodeIndex = opcodeIndex;
    }
}

enum Opcode {

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
        operation.getFullInput()[param0] = Advent_5.fakeUserInput;
        return null;
    }),

    /**
     * Outputs the value of the first parameter.
     */
    OUTPUT(4, 1, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        System.out.println(paramValue0);
        return null;
    }),

    /**
     * If the first parameter is non-zero, it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
     */
    JUMP_IF_TRUE(5, 2, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        if (0 != paramValue0) {
            return getParamValueFromMode(1, operation);
        }
        return null;
    }),

    /**
     * If the first parameter is zero, it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
     */
    JUMP_IF_FALSE(6, 2, (operation) -> {
        final Integer paramValue0 = getParamValueFromMode(0, operation);
        if (0 == paramValue0) {
            return getParamValueFromMode(1, operation);
        }
        return null;
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
        return null;
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
        return null;
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

    public Integer compute(final Operation operation) {
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

interface OperationStrategy {

    /**
     * Computes the given operation. If the input pointer need to be modified, it is returned. otherwise returns null.
     *
     * @param operation The operation to compute.
     * @return The new pointer value if needed, null otherwise.
     */
    Integer compute(Operation operation);
}
