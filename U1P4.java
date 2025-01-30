import java.util.*;

class U1P4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("let's get outta here"))
                break;

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
