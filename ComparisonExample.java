import java.util.Comparator;
import java.util.Arrays;  // for sort
import java.util.Objects; // for hash

public class ComparisonExample {
    public static class Point implements Comparable<Point> { // public for problem 14
        private int x;
        private int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public String toString() { return "(" + x + "," + y + ")"; }
        public boolean equals(Object other) {
            if (other == this) return true;
            if (other == null) return false;
            if (other.getClass() != this.getClass()) return false;
            Point that = (Point) other;
            return this.x == that.x && this.y == that.y;
        }
        public int hashCode() {
            return Objects.hash(x, y);
        }
        public int compareTo(Point that) { // assumes no overflow
            int ret = this.y - that.y;
            if (ret == 0)
                ret = this.x - that.x;
            return ret; 
        }
    }
    private static class PointXFirst implements Comparator<Point> {
        public int compare(Point p1, Point p2) { // assumes no overflow
            int ret = p1.x - p2.x;
            if (ret == 0)
                ret = p1.y - p2.y;
            return ret;
        }
    }

    public static void main(String[] args) {
        Point[] points = {
            new Point(1, 6),
            new Point(2, 5),
            new Point(3, 7),
            new Point(4, 4),
            new Point(2, 3),
            new Point(6, 5)
        };

        Arrays.sort(points); // comparable sort by y then by x
        System.out.println("Natural Comparable order (y then x):");
        System.out.println(Arrays.toString(points));

        Arrays.sort(points, new PointXFirst()); // comparator sort by x then y
        System.out.println("Comparator order (x then y):");
        System.out.println(Arrays.toString(points));
    }
}
