class Transition {
    // I don't care about the current state, so:
    String nextState;
    char currentChar;
    char newChar;
    Movement movement;
    boolean shouldEnd;

    public Transition(String nextState, char currentChar, char newChar, Movement movement, boolean shouldEnd) {
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
