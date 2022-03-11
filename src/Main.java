import Models.Address;
import Models.Electronics;

public class Main {
    public static void main(String[] args) {
        Electronics e = new Electronics(0, "Waschmaschine", 239.99, "Bosch", "Tolle Waschmashcine", 15, 5, "Model 1", 1350, "50x50x50cm");
        System.out.println(e);

        Address a = new Address(0, "Österriech", "6063", "Rum", "Erlenweg", "6");
    }
}
