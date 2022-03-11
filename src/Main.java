import Models.Clothing;
import Models.ClothingType;
import Models.Electronics;

public class Main {
    public static void main(String[] args) {
        Electronics e = new Electronics(0, "Waschmaschine", 239.99, "Bosch", "Tolle Waschmashcine", 15, 5, "Model 1", 1350, "50x50x50cm");
        Clothing c = new Clothing(1, "Jean test", 60.50, "Tommy Jeans", "Tolle Jeans zum Tragen für die Arbeit", 0.2, 3, ClothingType.JEANS, "Schwarz", 35, "Baumwolle");
        System.out.println(e);
        System.out.println(c);
    }
}
