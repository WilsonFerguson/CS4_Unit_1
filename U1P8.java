class U1P8 {

    public static void main(String[] args) {
        int numGenerated = 0;
        SimpleLinkedQueue<Integer> queue = new SimpleLinkedQueue<>();
        queue.push(1);
        int last = 1;
        while (numGenerated++ < 100) {
            int item = queue.pull();
            last = item;

            queue.push(5 * item);
            queue.push(5 * item + 1);
        }

        System.out.println(last);
    }
}
