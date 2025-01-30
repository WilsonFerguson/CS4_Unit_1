import java.util.*;
import java.io.*;

class TuringStacks {
    private static enum State {
        START,
        BACK,
    }

    private static enum Movement {
        LEFT,
        RIGHT,
        STAY,
    }

    private static class Command {
        // I don't care about the current state, so:
        State nextState;
        char currentChar;
        char newChar;
        Movement movement;
        boolean shouldEnd;

        public Command(State nextState, char currentChar, char newChar, Movement movement, boolean shouldEnd) {
            this.nextState = nextState;
            this.currentChar = currentChar;
            this.newChar = newChar;
            this.movement = movement;
            this.shouldEnd = shouldEnd;
        }

        @Override
        public String toString() {
            String output = "";
            output += "Next state: " + nextState;
            output += ", current char: " + currentChar;
            output += ", new char: " + newChar;
            output += ", movement: " + movement;
            output += ", should end: " + shouldEnd;
            return output;
        }
    }

    ArrayList<Command> startCommands = new ArrayList<>();
    ArrayList<Command> backCommands = new ArrayList<>();

    public TuringStacks(String[] input) {
        for (String line : input) {
            String[] instructions = line.split(",");
            // Determine which list it goes into
            boolean startCommand = instructions[0].equals("start");

            // Input char, output char
            char currentChar = instructions[1].charAt(0);
            char nextChar = instructions[3].charAt(0);

            // Output state
            State endCommand = getState(instructions[2]);

            // Movement
            Movement movement = getMovement(instructions[4]);

            // End program
            boolean shouldEnd = instructions[5].toLowerCase().charAt(0) == 'T';

            Command command = new Command(endCommand, currentChar, nextChar, movement, shouldEnd);
            if (startCommand) {
                startCommands.add(command);
            } else {
                backCommands.add(command);
            }
        }
    }

    public State getState(String input) {
        switch (input.toLowerCase()) {
            case "start":
                return State.START;
            case "back":
                return State.BACK;
            default:
                return State.BACK;
        }
    }

    public Movement getMovement(String input) {
        switch (input.toLowerCase()) {
            case "r":
                return Movement.RIGHT;
            case "l":
                return Movement.LEFT;
            case "n":
                return Movement.STAY;
            default:
                return Movement.STAY;
        }
    }

    @Override
    public String toString() {
        String output = "--Turing Stacks--";

        output += "\n  > Turning Stacks Start Commands-";
        for (Command command : startCommands) {
            output += "\n    > ";
            output += command.toString();
        }

        output += "\n  > Turning Stacks Back Commands-";
        for (Command command : backCommands) {
            output += "\n    > ";
            output += command.toString();
        }

        return output;
    }

    public static void main(String[] args) {
        String path = "ChangeZerosToOnes.txt";
        String[] input;
        try {
            Scanner scanner = new Scanner(new File(path));
            ArrayList<String> inputList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                inputList.add(scanner.nextLine());
            }
            scanner.close();

            input = new String[inputList.size()];
            for (int i = 0; i < input.length; i++) {
                input[i] = inputList.get(i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Yeah that file doesn't exist");
            return;
        }

        TuringStacks turingStacks = new TuringStacks(input);
        System.out.println(turingStacks);
    }
}
