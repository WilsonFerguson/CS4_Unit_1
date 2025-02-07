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

            Transition transition = new Transition(endState, currentChar, nextChar, movement);
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
        // Then we done
        if (transitionList == null) {
            return false;
        }

        char currentChar;
        if (rightTape.isEmpty()) {
            currentChar = ' ';
        } else {
            currentChar = rightTape.peek();
        }

        for (Transition transition : transitionList) {
            if (transition.currentChar != currentChar)
                continue;

            char newChar = transition.newChar;
            currentTransitionName = transition.nextState;

            switch (transition.movement) {
                case STAY:
                    rightTape.pop();
                    rightTape.push(newChar);
                    break;
                case RIGHT:
                    if (!rightTape.isEmpty()) {
                        rightTape.pop();
                    }
                    leftTape.push(newChar);
                    break;
                case LEFT:
                    if (!rightTape.isEmpty()) {
                        rightTape.pop();
                    }
                    rightTape.push(newChar);
                    if (!leftTape.isEmpty()) {
                        rightTape.push(leftTape.pop());
                    } else {
                        rightTape.push(' ');
                    }
                    break;
            }

            return true;
        }

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
            char c = leftTape.pop();
            if (c == ' ') {
                continue;
            }
            left.add(c);
        }
        System.out.println("Left size: " + left.size());

        for (int i = left.size() - 1; i >= 0; i--) {
            System.out.print(left.get(i));
            leftTape.push(left.get(i));
        }
    }

    public void printRightTape() {
        ArrayList<Character> right = new ArrayList<>();
        while (!rightTape.isEmpty()) {
            char c = rightTape.pop();
            if (c == ' ') {
                continue;
            }
            right.add(c);
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
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What file: ");
        String path = inputScanner.nextLine();
        // String path = "ChangeZerosToOnes.txt";
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
            inputScanner.close();
            return;
        }

        TuringStacks turingStacks = new TuringStacks(input);

        System.out.print("Input string: ");
        String inputString = inputScanner.nextLine();

        turingStacks.setInput(inputString);
        inputScanner.close();

        turingStacks.run();
    }
}
