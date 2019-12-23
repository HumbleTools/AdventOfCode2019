package fr.lma.advent.day7;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Operation {

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

    public OperationResult compute() {
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
