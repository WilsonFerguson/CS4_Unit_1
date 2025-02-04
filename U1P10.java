class U1P10 {
    class MyCircularQueue {

        int capacity;
        int[] array;
        int indexFront;
        int indexLast;

        boolean first;

        public MyCircularQueue(int k) {
            capacity = k;
            array = new int[k];
            indexFront = 0;
            indexLast = 0;

            first = true;
        }

        public boolean enQueue(int value) {
            if (first) {
                array[indexLast] = value;
                first = false;
                return true;
            }
            if ((indexLast + 1) % capacity == indexFront) {
                return false;
            }
            indexLast++;
            indexLast %= capacity;

            array[indexLast] = value;
            return true;
        }

        public boolean deQueue() {
            // If we move the front one to the right and we are past last, then we are size
            // = 0
            if ((indexFront + 1) % capacity > indexLast) {
                return false;
            }

            int value = array[indexFront]; // I guess it doesn't want this
            indexFront++;
            indexFront %= capacity;

            return true;
        }

        public int Front() {
            return array[indexFront];
        }

        public int Rear() {
            return array[indexLast];
        }

        public boolean isEmpty() {
            return indexLast == indexFront + 1;
        }

        public boolean isFull() {
            return indexFront == indexLast + 1;
        }
    }

    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */

    public static void main(String[] args) {

    }
}
