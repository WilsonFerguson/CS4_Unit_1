import java.util.ArrayList;

class U1P6 {

    class MinStack {
        class Entry {
            int value;
            int min;

            public Entry(int value, int min) {
                this.value = value;
                this.min = min;
            }
        }

        ArrayList<Entry> stack;
        int min;

        public MinStack() {
            stack = new ArrayList<Entry>();
            min = Integer.MAX_VALUE;
        }

        public void push(int val) {
            if (val < min) {
                min = val;
            }
            stack.add(new Entry(val, min));
        }

        public void pop() {
            stack.remove(stack.size() - 1);
            if (stack.size() == 0) {
                min = Integer.MAX_VALUE;
            } else {
                min = stack.get(stack.size() - 1).min;
            }
        }

        public int top() {
            return stack.get(stack.size() - 1).value;
        }

        public int getMin() {
            return min;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new U1P6().new MinStack(); // Dang that's elegant right there
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // -3
        minStack.pop();
        System.out.println(minStack.top()); // 0
        System.out.println(minStack.getMin()); // -2
    }
}
