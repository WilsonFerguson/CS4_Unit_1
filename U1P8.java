class U1P8 {

    public static void main(String[] args) {
        int numGenerated = 0;
        SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<>();
        SimpleLinkedStack<Integer> stack = new SimpleLinkedStack<>();
        queue.push(1);
        while (numGenerated++ < 100) {
            int item = queue.pull();
            // System.out.print(item);
            stack.push(item);
            queue.push(5 * item);
            queue.push(5 * item + 1);
        }

        System.out.println(stack.peek());
    }
}
