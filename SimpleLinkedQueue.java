import java.util.ArrayList;

class SimpleLinkedQueue<T> implements SimpleQueue<T> {
    ArrayList<T> queue;

    public SimpleLinkedQueue() {
        queue = new ArrayList<>();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public T peek() {
        return queue.get(0);
    }

    public void push(T item) {
        queue.add(item);
    }

    public T pull() {
        T item = queue.get(0);
        queue.remove(0);
        return item;
    }

    public void clear() {
        queue.clear();
    }

    public static void main(String[] args) {

    }
}
