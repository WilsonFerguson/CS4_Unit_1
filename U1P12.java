import java.util.ArrayList;
import java.util.Stack;

class U1P12 {

    class MyQueue {

        Stack<Integer> main;
        Stack<Integer> rev;

        public MyQueue() {
            main = new Stack<>();
            rev = new Stack<>();
        }

        public void push(int x) {
            main.add(x);
        }

        // We only need to add from main when rev is empty because we always pull ones
        // that were first added!
        public int pop() {
            if (rev.isEmpty()) {
                while (!main.isEmpty()) {
                    rev.add(main.pop());
                }
            }

            return rev.pop();
        }

        public int peek() {
            if (rev.isEmpty()) {
                while (!main.isEmpty()) {
                    rev.add(main.pop());
                }
            }

            return rev.peek();
        }

        public boolean empty() {
            return main.isEmpty() && rev.isEmpty();
        }
    }
}
