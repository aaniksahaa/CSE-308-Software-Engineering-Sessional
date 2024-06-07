package util;

import java.util.List;

public class Instruction {
    private String command;
    private List<String> arguments;

    public Instruction(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
