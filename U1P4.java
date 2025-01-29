import java.util.*;

class U1P4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a string: ");
            String input = scanner.next();

            // Or just ctrl + c to kill it
            if (input.equals("exit")) {
                break;
            }

            SimpleLinkedStack<Character> stack = new SimpleLinkedStack<>();
            for (char c : input.toCharArray()) {
                if (c != '-') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        System.out.print("!");
                        break;
                    } else {
                        System.out.print(stack.pop());
                    }
                }
            }
            System.out.println("");

        }
        scanner.close();
    }
}
