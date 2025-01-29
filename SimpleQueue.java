public interface SimpleQueue<E> {
    public boolean isEmpty(); // tests if empty
    public E peek(); // returns head element
    public void push(E e); // pushes element to tail
    public E pull(); // removes and returns head element
    public void clear(); // removes all elements
}
