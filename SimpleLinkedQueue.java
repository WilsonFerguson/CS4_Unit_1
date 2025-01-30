import java.util.ArrayList;

class SimpleLinkedQueue<T> implements SimpleQueue<T> {
    private static class LinkedGuy<E> {
        public E value;
        public LinkedGuy<E> next;

        public LinkedGuy(E value, LinkedGuy<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    LinkedGuy<T> top;

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public T peek() {
        return top.value;
    }

    @Override
    public void push(T e) {
        if (top == null) {
            top = new LinkedGuy<T>(e, null);
            return;
        }

        LinkedGuy<T> wereDivingIn = top;
        while (wereDivingIn.next != null) {
            wereDivingIn = wereDivingIn.next;
        }

        wereDivingIn.next = new LinkedGuy<T>(e, null);
    }

    @Override
    public T pull() {
        T guyToSend = top.value;
        top = top.next;
        return guyToSend;
    }

    @Override
    public void clear() {
        top = null;
    }

    public String toString() {
        LinkedGuy<T> test = top;
        String output = "";
        while (test != null) {
            output += test.value + ", ";
            test = test.next;
        }
        return output;
    }

    public static void main(String[] args) {
        // This whole thing is stolen from geeksforgeeks
        // This whole thing is stolen from geeksforgeeks
        // This whole thing is stolen from geeksforgeeks

        SimpleLinkedQueue<String> queue = new SimpleLinkedQueue<>();

        // add elements to the queue
        queue.push("apple");
        queue.push("banana");
        queue.push("cherry");

        // print the queue
        System.out.println("Queue: " + queue);

        // remove the element at the front of the queue
        String front = queue.pull();
        System.out.println("Removed element: " + front);

        // print the updated queue
        System.out.println("Queue after removal: " + queue);

        // add another element to the queue
        queue.push("date");

        // peek at the element at the front of the queue
        String peeked = queue.peek();
        System.out.println("Peeked element: " + peeked);

        // print the guy out
        System.out.println("Queue: " + queue);
    }
}
