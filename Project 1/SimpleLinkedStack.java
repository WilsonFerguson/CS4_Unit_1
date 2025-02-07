public class SimpleLinkedStack<E> implements SimpleStack<E> {
    private static class LinkedElement<E> {
        public E e;
        public LinkedElement<E> next;

        public LinkedElement(E e, LinkedElement<E> next) {
            this.e = e;
            this.next = next;
        }
    }

    private LinkedElement<E> top;

    public SimpleLinkedStack() { // equivalent to default
        top = null;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public E peek() {
        return top.e;
    }

    @Override
    public void push(E e) {
        LinkedElement<E> newTop = new LinkedElement<>(e, top);
        top = newTop;
    }

    @Override
    public E pop() {
        E topE = top.e;
        top = top.next;
        return topE;
    }

    @Override
    public void clear() {
        top = null;
    }

    public static void main(String[] args) {
        // A test that includes all methods
        SimpleLinkedStack<Integer> stack = new SimpleLinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        String out = "" + stack.peek();
        while (!stack.isEmpty()) {
            out += stack.pop();
        }
        stack.push(4);
        stack.push(5);
        stack.push(6);
        out += stack.peek();
        stack.clear();
        out += stack.isEmpty() ? "G" : "B";
        stack.push(7);
        out += stack.pop();
        String expected = "33216G7";
        if (out.equals(expected)) {
            System.out.println("Test passed: " + out);
        } else {
            System.out.println("Test failed.  Expected: " + expected + ", actual " + out);
        }
    }

}
