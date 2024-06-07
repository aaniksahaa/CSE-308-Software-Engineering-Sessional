package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructionParser {
    public static void main(String[] args) {
        // Example usage:
        String input = "V";
        Instruction instruction = parseInstruction(input);

        if (instruction != null) {
            System.out.println("Parsed Command: " + instruction.getCommand());
            System.out.println("Parsed Arguments: " + instruction.getArguments());
        } else {
            System.out.println("Invalid instruction format");
        }
    }

    // Static method to parse the instruction
    public static Instruction parseInstruction(String input) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0];
        List<String> arguments = new ArrayList<>();
        if (parts.length == 2) {
            String argumentString = parts[1];
            arguments = Arrays.asList(argumentString.split("\\s+"));
        }

        return new Instruction(command, arguments);
    }
}
