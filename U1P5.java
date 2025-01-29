import java.util.*;

class U1P5 {
    public static void main(String[] args) {
        computeMe("7 4 5 + 6 - *");
    }

    public static void computeMe(String input) {
        SimpleLinkedStack<Integer> operands = new SimpleLinkedStack<Integer>();

        for (String str : input.split(" ")) {
            char c = str.charAt(0);

            if (c >= '0' && c <= '9') {
                operands.push(c - '0');
            } else {
                // Give me the top 2
                Integer second = operands.pop();
                Integer first = operands.pop();

                Integer output = 0;
                switch (c) {
                    case '-':
                        output = first - second;
                        break;
                    case '+':
                        output = first + second;
                        break;
                    case '*':
                        output = first * second;
                        break;
                    case '/':
                        output = first / second;
                    default:
                        System.out.println("What kinda operator you using?");
                        break;
                }

                operands.push(output);
            }
        }

        // Okay we should have a length of 1
        System.out.println(operands.pop());
    }
}
