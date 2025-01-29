public class Pair<T, U> { // T and U are type parameters
    private T first; // Inside the class, we use type parameters like types
    private U second;
    public Pair(T first, U second) {
        this.first = first; // remember parameters hide attributes with the same name
        this.second = second;
    }
    public T getFirst() { return first; }
    public U getSecond() { return second; }

    public static void main(String[] args) {
        // Here we make a pair with a String and an Integer, note 117 is autoboxed
        Pair<String, Integer> p = new Pair<>("Spartan", 117);
        String s = p.getFirst();
        int i = p.getSecond(); // unboxing
        System.out.println(s + " " + i); // prints Spartan 117
    }
}
