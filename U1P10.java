class U1P10 {
    class MyCircularQueue {

        int capacity;
        int[] array;
        int indexFront;
        int indexLast;

        public MyCircularQueue(int k) {
            capacity = k;
            array = new int[k];
            for (int i = 0; i < k; i++) {
                array[i] = -1;
            }
            indexFront = 0;
            indexLast = 0;
        }

        public boolean enQueue(int value) {
            if (isFull())
                return false;

            array[indexLast] = value;
            indexLast = (indexLast + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty())
                return false;

            array[indexFront] = -1;
            indexFront = (indexFront + 1) % capacity;
            return true;
        }

        public int Front() {
            return array[indexFront];
        }

        public int Rear() {
            if (indexLast == 0)
                return array[capacity - 1];
            return array[indexLast - 1];
        }

        public boolean isEmpty() {
            return indexFront == indexLast && array[indexFront] == -1;
        }

        public boolean isFull() {
            return indexFront == indexLast && array[indexFront] != -1;
        }
    }

    public static void main(String[] args) {

    }
}
