import java.io.*;

class U1P4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("U1P4In.txt"));

        String input;
        while ((input = reader.readLine()) != null) {

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

        reader.close();
    }
}
