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
        for (int i = input.length() - 1; i >= 0; i--) {
            rightTape.push(input.toCharArray()[i]);
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
     * @return True if successful, False if it doesn't find a valid transition
     */
    public boolean step() {
        ArrayList<Transition> transitionList = transitions.get(currentTransitionName);
        // Then we done
        if (transitionList == null) {
            return false;
        }

        char currentChar = rightTape.pop();

        for (Transition transition : transitionList) {
            if (transition.currentChar != currentChar)
                continue;

            char newChar = transition.newChar;
            currentTransitionName = transition.nextState;

            switch (transition.movement) {
                case STAY:
                    rightTape.push(newChar);
                    break;
                case RIGHT:
                    leftTape.push(newChar);
                    if (rightTape.isEmpty()) {
                        rightTape.push(' ');
                    }
                    break;
                case LEFT:
                    rightTape.push(newChar);

                    rightTape.push(leftTape.pop());

                    if (leftTape.isEmpty()) {
                        leftTape.push(' ');
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
            if (c == ' ')
                break;
            left.add(c);
        }

        for (int i = left.size() - 1; i >= 0; i--) {
            System.out.print(left.get(i));
            leftTape.push(left.get(i));
        }
    }

    public void printRightTape() {
        ArrayList<Character> right = new ArrayList<>();
        while (!rightTape.isEmpty()) {
            char c = rightTape.pop();
            right.add(c);
        }

        for (int i = 0; i < right.size(); i++) {
            System.out.print(right.get(i));
        }
        for (int i = right.size() - 1; i >= 0; i--) {
            rightTape.push(right.get(i));
        }
    }

    public void printTapeWithCursor() {
        ArrayList<Character> left = new ArrayList<>();
        while (!leftTape.isEmpty()) {
            char c = leftTape.pop();
            left.add(c);
        }

        for (int i = left.size() - 1; i >= 0; i--) {
            System.out.print(left.get(i));
            leftTape.push(left.get(i));
        }

        ArrayList<Character> right = new ArrayList<>();
        while (!rightTape.isEmpty()) {
            char c = rightTape.pop();
            right.add(c);
        }

        for (int i = 0; i < right.size(); i++) {
            System.out.print(right.get(i));
        }
        for (int i = right.size() - 1; i >= 0; i--) {
            rightTape.push(right.get(i));
        }
        System.out.println(" (" + currentTransitionName + ")");
        System.out.println(" ".repeat(left.size()) + "^");
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
        String[] input;

        try {
            Scanner scanner = new Scanner(new File(path));
            ArrayList<String> inputList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0)
                    continue;
                inputList.add(line);
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
