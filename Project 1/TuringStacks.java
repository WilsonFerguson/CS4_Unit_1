import java.util.*;
import java.io.*;

class TuringStacks {

    HashMap<String, ArrayList<Transition>> transitions;

    SimpleLinkedStack<Character> leftTape;
    SimpleLinkedStack<Character> rightTape;

    public TuringStacks(String[] transitions) {
        this.transitions = new HashMap<>();
        leftTape = new SimpleLinkedStack<>();
        rightTape = new SimpleLinkedStack<>();

        for (String line : transitions) {
            String[] instructions = line.split(",");

            String transitionName = instructions[0];

            // Input char, output char
            char currentChar = instructions[1].charAt(0);
            char nextChar = instructions[3].charAt(0);

            // Output state
            State endtransition = getState(instructions[2]);

            // Movement
            Movement movement = getMovement(instructions[4]);

            // End program
            boolean shouldEnd = instructions[5].toLowerCase().charAt(0) == 't';

            Transition transition = new Transition(endtransition, currentChar, nextChar, movement, shouldEnd);
            ArrayList<Transition> transitionList = this.transitions.get(transitionName);
            if (transitionList == null) {
                transitionList = new ArrayList<>();
                transitionList.add(transition);
                this.transitions.put(transitionName, transitionList);
            } else {
                transitionList.add(transition);
            }
        }
    }

    public void setInput(String input) {
        for (char c : input.toCharArray()) {
            rightTape.push(c);
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

    /**
     * Performs one transition.
     */
    public void step() {

    }

    public void printTransitions() {
        System.out.println("--Turing Stacks--");

        for (String key : transitions.keySet()) {
            System.out.println("  > Transition List \"" + key + "\":");
            for (Transition transition : transitions.get(key)) {
                System.out.println("    - " + transition.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "";
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
        turingStacks.printTransitions();
    }
}
