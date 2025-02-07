class Transition {
    // I don't care about the current state, so:
    String nextState;
    char currentChar;
    char newChar;
    Movement movement;

    public Transition(String nextState, char currentChar, char newChar, Movement movement) {
        this.nextState = nextState;
        this.currentChar = currentChar;
        this.newChar = newChar;
        this.movement = movement;
    }

    @Override
    public String toString() {
        String output = "";
        output += "Next state: " + nextState;
        output += ", current char: " + currentChar;
        output += ", new char: " + newChar;
        output += ", movement: " + movement;
        return output;
    }
}
