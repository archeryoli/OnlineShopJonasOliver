import Models.*;

public class Main {
    public static void main(String[] args) {
        // Fehler da abstract
        //Article a = new Article(0, "Neues Product", 110.99, "TestFirma", "ProductTest", 1.5, 5);
        Article e = new Electronics(0, "Waschmaschine", 110.99, "Bosch", "ProductTest", 1.5, 5, "XYZA12Z34", 1500, "50x50x50");
        System.out.println(e);
        Article c = new Clothing(1, "Pullover Fantastico", 420.69, "Fantastico Luis Vitton", "Toller Pullover bitte kaufen", 0.3, 42, ClothingType.HODDIE, "Schwarz/Black", 172, "Baumwolle");
        System.out.println(c);
        Article b = new Book(2, "Harry Potter and the Order of Pheonix", 23.50, "", "Tolles Buch für Kinder wie Jonas", 0.5, 5, "123456", "Harry Potter and the Order of Pheonix", "J.K. Rowling", 690, "MusterVerlag");
        System.out.println(b);
    }
}
