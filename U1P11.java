import java.util.ArrayDeque;

// I'm not exactly sure why a deque is better than just an arraylist for this?
class RecentCounter {

    ArrayDeque<Integer> pings;

    public RecentCounter() {
        pings = new ArrayDeque<>();
    }

    public int ping(int t) {
        pings.addFirst(t);

        while (pings.peekLast() < t - 3000) {
            pings.pollLast();
        }

        return pings.size();
    }
}
