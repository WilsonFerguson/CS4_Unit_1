import java.util.*;

class U1P5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (!computeMe(input)) {
                break;
            }
        }
        scanner.close();
    }

    public static boolean computeMe(String input) {
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

        if (operands.isEmpty())
            return false;

        int answer = operands.pop();
        if (!operands.isEmpty()) {
            return false;
        }

        System.out.println(answer);
        return true;
    }
}
