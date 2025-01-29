public interface SimpleStack<E> {
    public boolean isEmpty(); // tests if empty
    public E peek(); // returns top element
    public void push(E e); // pushes element on top
    public E pop(); // removes and returns top element
    public void clear(); // removes all elements
}