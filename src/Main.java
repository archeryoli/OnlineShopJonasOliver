import Models.*;

public class Main {
    public static void main(String[] args) {
        Electronics e = new Electronics(0, "Waschmaschine", 239.99, "Bosch", "Tolle Waschmashcine", 15, 5, "Model 1", 1350, "50x50x50cm");
        Clothing c = new Clothing(1, "Jean test", 60.50, "Tommy Jeans", "Tolle Jeans zum Tragen für die Arbeit", 0.2, 3, ClothingType.JEANS, "Schwarz", 35, "Baumwolle");
        System.out.println(e);

        Address a = new Address(0, "Österriech", "6063", "Rum", "Erlenweg", "6");
        System.out.println(c);
        System.out.println();
        System.out.println(a);
        Book b = new Book("123456", "Harry Potter and the Order of Pheonix", "J.K. Rowling", 690, "MusterVerlag");
        System.out.println(b);
    }
}
