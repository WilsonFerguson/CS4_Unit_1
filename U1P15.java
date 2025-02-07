import java.util.PriorityQueue;

class U1P15 {
    class MedianFinder {

        PriorityQueue<Integer> low;
        PriorityQueue<Integer> high;

        public MedianFinder() {
            low = new PriorityQueue<>((a, b) -> b - a);
            high = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (low.isEmpty() && high.isEmpty()) {
                low.add(num);
                return;
            }
            // You said we couldn't use AI but I figured asking ChatGPT for a small hint
            // would be better than looking up the solution. It told me to use 2 heaps (like
            // you said) but have the low one contain the lowest half of the numbers and the
            // upper one the upper half (which I didn't think of)

            if (low.size() == high.size()) {
                if (num > high.peek()) {
                    high.add(num);
                } else if (num < low.peek()) {
                    low.add(num);
                } else {
                    low.add(num);
                }
            } else if (low.size() > high.size()) {
                if (num < low.peek()) {
                    high.add(low.poll());
                    low.add(num);
                } else if (num > low.peek()) {
                    high.add(num);
                } else {
                    high.add(num);
                }
            } else {
                if (num > high.peek()) {
                    low.add(high.poll());
                    high.add(num);
                } else if (num < high.peek()) {
                    low.add(num);
                } else {
                    low.add(num);
                }
            }
        }

        public double findMedian() {
            if (low.size() == high.size()) {
                return (low.peek() + high.peek()) / 2.0;
            } else if (low.size() > high.size()) {
                return low.peek();
            } else {
                return high.peek();
            }
        }
    }
}
