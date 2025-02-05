import java.util.ArrayList;
import java.util.Stack;

class U1P12 {

    // Okay it may be really bad code but it did beat 100% of people so...
    class MyQueue {

        Stack<Integer> main;
        Stack<Integer> rev;
        boolean upToDate;
        boolean upToDateMain;

        public MyQueue() {
            main = new Stack<>();
            rev = new Stack<>();

            upToDate = false;
            upToDateMain = true;
        }

        public void push(int x) {
            if (!upToDateMain) {
                updateMain();
            }

            main.add(x);
            upToDate = false;
        }

        private void updateMain() {
            main.clear();

            int size = rev.size();
            for (int i = 0; i < size; i++) {
                main.add(rev.pop());
            }

            upToDateMain = true;
            upToDate = false; // Cause we just nuked the rev
        }

        private void updateRev() {
            rev.clear();

            ArrayList<Integer> mainTemp = new ArrayList<>();
            int size = main.size();
            for (int i = 0; i < size; i++) {
                int val = main.pop();
                rev.add(val);
                mainTemp.add(val);
            }

            for (int i = mainTemp.size() - 1; i >= 0; i--) {
                main.add(mainTemp.get(i));
            }

            upToDate = true;
        }

        public int pop() {
            if (upToDate) {
                upToDateMain = false;
                return rev.pop();
            }

            updateRev();
            upToDateMain = false;
            return rev.pop();
        }

        public int peek() {
            if (upToDate) {
                return rev.peek();
            }

            updateRev();
            return rev.peek();
        }

        public boolean empty() {
            if (!upToDateMain)
                updateMain();
            return main.isEmpty();
        }
    }

}
