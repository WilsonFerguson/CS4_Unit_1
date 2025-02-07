/* CS4 Unit 1 problem 14 starter.  Fill in the details.
 * Name: Wilson Ferguson
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections; // for reverseOrder
import java.util.Random; // for generating test data in main
import java.util.Arrays; // for testing

public class Heap<E> implements SimpleQueue<E> {
    private ArrayList<E> elements; // use this to store the elements
    private Comparator<E> comparator; // if null, use natural order

    public Heap() {
        elements = new ArrayList<>();
        comparator = null;
    }

    public Heap(Comparator<E> comparator) {
        elements = new ArrayList<>();
        this.comparator = comparator;
    }

    @Override
    public boolean isEmpty() {
        return elements.size() == 0;
    }

    @Override
    public E peek() {
        return elements.get(0);
    }

    private boolean greater(E one, E two) {
        if (comparator != null) {
            return comparator.compare(one, two) > 0;
        } else {
            return ((Comparable<E>) one).compareTo(two) > 0;
        }
    }

    @Override
    public void push(E e) {
        elements.add(e);

        // (k - 1)/2 is our parent
        int myIndex = elements.size() - 1;
        int parentIndex = (myIndex - 1) / 2;

        while (myIndex > 0 && greater(elements.get(parentIndex), e)) {
            elements.set(myIndex, elements.get(parentIndex));
            elements.set(parentIndex, e);
            myIndex = parentIndex;
            parentIndex = (myIndex - 1) / 2;
        }
    }

    @Override
    public E pull() {
        E result = elements.get(0);
        elements.set(0, elements.get(elements.size() - 1));
        elements.remove(elements.size() - 1);

        int myIndex = 0;
        while (true) {
            int childLeft = myIndex * 2 + 1;
            int childRight = myIndex * 2 + 2;

            // If no left child, we're done
            if (childLeft >= elements.size()) {
                break;
            }

            // If no right child, only check left child
            if (childRight >= elements.size()) {
                if (greater(elements.get(myIndex), elements.get(childLeft))) {
                    E temp = elements.get(myIndex);
                    elements.set(myIndex, elements.get(childLeft));
                    elements.set(childLeft, temp);
                    myIndex = childLeft;
                    continue;
                } else {
                    break;
                }
            }

            // If both children exist, swap with the smaller child
            E childLeftValue = elements.get(childLeft);
            E childRightValue = elements.get(childRight);

            // If I am smaller than both children, we're done
            if (greater(childLeftValue, elements.get(myIndex)) && greater(childRightValue, elements.get(myIndex))) {
                break;
            }

            // Otherwise swap with the smaller child
            if (greater(childLeftValue, childRightValue)) {
                elements.set(myIndex, childRightValue);
                elements.set(childRight, elements.get(myIndex));
                myIndex = childRight;
            } else {
                elements.set(myIndex, childLeftValue);
                elements.set(childLeft, elements.get(myIndex));
                myIndex = childLeft;
            }
        }

        return result;
    }

    @Override
    public void clear() {
        elements.clear();
    }

    private static void sortWithHeap(ComparisonExample.Point[] points, Comparator<ComparisonExample.Point> comparator) {
        Heap<ComparisonExample.Point> heap;
        if (comparator == null)
            heap = new Heap<>();
        else
            heap = new Heap<>(comparator);

        for (ComparisonExample.Point p : points) {
            heap.push(p);
        }

        for (int i = 0; i < points.length; i++) {
            points[i] = heap.pull();
        }

    }

    public static void main(String[] args) {
        // Tests your heap and comparator by using them to sort many random points
        // two different ways and checking the order.
        Random random = new Random(); // could pass a seed value for deterministic results
        ComparisonExample.Point[] points = new ComparisonExample.Point[100000];
        for (int i = 0; i < points.length; i++) {
            int x = random.nextInt(-1000, 1000);
            int y = random.nextInt(-1000, 1000);
            points[i] = new ComparisonExample.Point(x, y);
        }

        boolean passed = true;
        // First sort in natural order (pass null for comparator)
        sortWithHeap(points, null);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) < 0) {
                System.out.println("BAD NATURAL ORDER");
                passed = false;
                break;
            }
        }
        if (passed)
            System.out.println("good natural order");

        boolean passedReverse = true;
        // Second sort in reverse natural order using a standard comparator

        sortWithHeap(points, Collections.reverseOrder());
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) > 0) {
                System.out.println("BAD REVERSE ORDER");
                passedReverse = false;
                break;
            }
        }
        if (passedReverse)
            System.out.println("good reverse order");

        // TODO (optional): Write and use your own reverse order comparator for points
    }
}
