import Json.JsonReader;
import Json.JsonWriter;
import Models.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        User u1 = new User(1, "Jonas", "Kirchmair", LocalDate.of(2005, 05, 06), Gender.male, "jonas1.kirchmair@gmail.com", "hallo1234", null, null);
        System.out.println(u1);



        List<Address> addresses = new ArrayList<Address>();
        Address a1 = new Address(1, "Österreich", "6063", "Rum", "Erlenweg", "6");
        Address a2 = new Address(2, "Österreich", "6235", "Reith im Alpbachtal", "Hygna", "24");
        Address a3 = new Address(3, "Österreich", "6020", "Innsbruck", "Anichstraße", "13b");

        addresses.add(a1);
        addresses.add(a2);
        u1.setAddresses(addresses);

        u1.addAddress(a3);
        System.out.println(u1);


        // Fehler da abstract
        //Article a = new Article(0, "Neues Product", 110.99, "TestFirma", "ProductTest", 1.5, 5);
        Article e = new Electronics(0, "Waschmaschine", 110.99, "Bosch", "ProductTest", 1.5, 5, "XYZA12Z34", 1500, "50x50x50");
        System.out.println(e);
        Article c = new Clothing(1, "Pullover Fantastico", 420.69, "Fantastico Luis Vitton", "Toller Pullover bitte kaufen", 0.3, 42, ClothingType.HODDIE, "Schwarz/Black", 172, "Baumwolle");
        System.out.println(c);
        Article b = new Book(2, "Harry Potter and the Order of Pheonix", 23.50, "", "Tolles Buch für Kinder wie Jonas", 0.5, 5, "123456", "Harry Potter and the Order of Pheonix", "J.K. Rowling", 690, "MusterVerlag");
        System.out.println(b);

        Basket basket = new Basket();
        u1.setBasket(basket);
        u1.getBasket().addBasketHashMapEntry(e, 2);
        u1.getBasket().addBasketHashMapEntry(c, 3);
        System.out.println();
        System.out.println(u1);

        RuntimeTypeAdapterFactory<Article> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Article.class,"type")
                .registerSubtype(Electronics.class, "Electronics")
                .registerSubtype(Clothing.class, "Clothing")
                .registerSubtype(Book.class, "Book");
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
        String json = gson.toJson(basket);
        System.out.printf("JSON: %s \n", json);

        JsonWriter.writeObjectToJson(basket, Path.of("basket.json"));


        Basket basket2 = JsonReader.getObjectFromFile(Path.of("basket.json"));
        System.out.println();
        System.out.println(basket2);
        System.out.println(basket);




    }
}
