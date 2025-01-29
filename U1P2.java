import java.util.*;

class U1P2 {
    public static Double findMe(ArrayList<Pair<String, Double>> constants, String constant) {
        for (Pair<String, Double> con : constants) {
            if (con.getFirst().toLowerCase().equals(constant.toLowerCase()))
                return con.getSecond();
        }

        return Double.MIN_VALUE;
    }

    public static void main(String[] args) {
        ArrayList<Pair<String, Double>> constants = new ArrayList<>();
        constants.add(new Pair<String, Double>("Pi", 3.14159));
        constants.add(new Pair<String, Double>("Tau", 6.28318));
        constants.add(new Pair<String, Double>("Sqrt(2)", 1.41421));
        constants.add(new Pair<String, Double>("Phi", 1.61803));
        constants.add(new Pair<String, Double>("Stefan-boltzmann Constant", 5.67037 * Math.pow(10, -8)));
        constants.add(new Pair<String, Double>("Coulomb Constant", 8.98755 * Math.pow(10, 9)));
        constants.add(new Pair<String, Double>("Ln(2)", 0.69314));
        constants.add(new Pair<String, Double>("e", 2.71828));
        constants.add(new Pair<String, Double>("c", 299792458.0));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a constant name: ");
        String constant = scanner.next();

        // System.out.println(findMe(constants, constant));
        Double value = findMe(constants, constant);
        if (value == Double.MIN_VALUE)
            System.out.println("A not found message");
        else
            System.out.println(value);

        scanner.close();
    }
}
