import java.util.*;
import java.io.*;

class TuringStacks {

    String currentTransitionName = "start";
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
            String endState = instructions[2];

            // Movement
            Movement movement = getMovement(instructions[4]);

            // End program
            boolean shouldEnd = instructions[5].toLowerCase().charAt(0) == 't';

            Transition transition = new Transition(endState, currentChar, nextChar, movement, shouldEnd);
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
        currentTransitionName = "start";
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
     * 
     * @return True if successful, False if it fails to run
     */
    public boolean step() {
        ArrayList<Transition> transitionList = transitions.get(currentTransitionName);
        if (transitionList == null) {
            System.out.println("No transition list found for \"" + currentTransitionName + "\"");
            return false;
        }

        // TODO: For current char, don't always check right but check left if we are
        // moving left.
        char currentChar;
        if (rightTape.isEmpty()) {
            currentChar = ' ';
        } else {
            currentChar = rightTape.pop();
        }
        for (Transition transition : transitionList) {
            if (transition.currentChar != currentChar)
                continue;

            currentChar = transition.newChar;
            currentTransitionName = transition.nextState;
            switch (transition.movement) {
                case LEFT:
                    // Put it back
                    rightTape.push(currentChar);
                    // Move over
                    if (leftTape.isEmpty()) {
                        rightTape.push(' ');
                    } else {
                        rightTape.push(leftTape.pop());
                    }
                    break;
                case RIGHT:
                    // Move over
                    leftTape.push(currentChar);
                    break;
                case STAY:
                    // Put it back
                    rightTape.push(currentChar);
                    break;
            }

            if (transition.shouldEnd) {
                return false;
            }

            return true;
        }

        System.out.println(
                "No transition found for \"" + currentChar + "\" in transition list \"" + currentTransitionName + "\"");
        return false;
    }

    public void run() {
        while (true) {
            boolean result = step();
            if (!result) {
                printTape();
                System.out.println();
                System.out.println("Okay cya");
                return;
            }
        }
    }

    public void printTape() {
        printLeftTape();
        printRightTape();
    }

    public void printLeftTape() {
        ArrayList<Character> left = new ArrayList<>();
        while (!leftTape.isEmpty()) {
            left.add(leftTape.pop());
        }

        for (int i = left.size() - 1; i >= 0; i--) {
            System.out.print(left.get(i));
            leftTape.push(left.get(i));
        }
    }

    public void printRightTape() {
        ArrayList<Character> right = new ArrayList<>();
        while (!rightTape.isEmpty()) {
            right.add(rightTape.pop());
        }

        for (int i = right.size() - 1; i >= 0; i--) {
            System.out.print(right.get(i));
            rightTape.push(right.get(i));
        }
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

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input string: ");
        String inputString = scanner.nextLine();

        turingStacks.setInput(inputString);
        scanner.close();

        turingStacks.run();
    }
}
