import Json.JsonReader;
import Json.JsonWriter;
import Models.*;
import Models.DB.IRepositoryOnlineshop;
import Models.DB.RepositoryOnlineshopDB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Article> listOfAllArticles = new ArrayList<>();
    public static void main(String[] args) {
        init();
        System.out.println("Olineshop von Jonas und Oliver");
        System.out.println("==============================");

        switch (showLoginMenu()){
            case 'a':
                break;
            case 'r':
                registerUser();
                break;
            case 'b':
                System.out.println("Danke für deinen Besuch");
                System.exit(0);
        }

        //showAllArticles();
        /*
        User u1 = new User(1, "Jonas", "Kirchmair", LocalDate.of(2005, 05, 06), Gender.male, "jonas1.kirchmair@gmail.com", "hallo1234", null, null);
        System.out.println(u1);



        List<Address> addresses = new ArrayList<Address>();
        Address a1 = new Address(1, "Österreich", "6063", "Rum", "Erlenweg", "6");
        Address a2 = new Address(2, "Österreich", "6235", "Reith im Alpbachtal", "Hygna", "24");
        Address a3 = new Address(3, "Österreich", "6020", "Innsbruck", "Anichstraße", "13b");
        Address a4 = new Address(4, "Österreich", "6040", "Hall", "Oberer Stadtplatz", "5");

        addresses.add(a1);
        addresses.add(a2);
        u1.setAddresses(addresses);

        u1.addAddress(a3);
        System.out.println(u1);

         */

        // Fehler da abstract
        //Article a = new Article(0, "Neues Product", 110.99, "TestFirma", "ProductTest", 1.5, 5);
        Article e = new Electronics(0, "Waschmaschine", 110.99, "Bosch", "ProductTest", 1.5, 5, "XYZA12Z34", 1500, "50x50x50");
        System.out.println(e);
        Article c = new Clothing(1, "Pullover Fantastico", 420.69, "Fantastico Luis Vitton", "Toller Pullover bitte kaufen", 0.3, 42, ClothingType.HODDIE, "Schwarz/Black", 172, "Baumwolle");
        System.out.println(c);
        Article b = new Book(2, "Harry Potter and the Order of Pheonix", 23.50, "", "Tolles Buch für Kinder wie Jonas", 0.5, 5, "123456", "Harry Potter and the Order of Pheonix", "J.K. Rowling", 690, "MusterVerlag");
        System.out.println(b);

    }
    private static void init(){
        listOfAllArticles.add(new Electronics(1, "Waschmaschine", 299.99, "Bosch", "Waschmaschine mit Anti-bügel Funktion", 150, 7, "XA6381", 950, "60x70x80"));
        listOfAllArticles.add(new Electronics(2, "Gaming PC", 1585, "Hofer", "Gaming PC für tolle Bilder zum Arbeiten und gamen", 12, 3, "AMD1", 600, "25x60x50"));
        listOfAllArticles.add(new Electronics(3, "Azer Bildschirm", 150.85, "Azer", "Curved Gaming PC mit 144Hz und 27'", 2, 25, "AZER1234", 30, "60x10x30"));

        listOfAllArticles.add(new Clothing(4, "Sommershirt", 23.50, "Levis", "Shirt für warme Sommertage", 0.6, 2, ClothingType.TSHIRT, "rot", 162, "Baumwolle"));
        listOfAllArticles.add(new Clothing(5, "Anzugsjeans", 65.90, "Tommy Jeans", "Für besondere Anlässe", 1.5, 5, ClothingType.JEANS, "schwarz", 36, "Baumwolle"));
        listOfAllArticles.add(new Clothing(6, "Flip Flop", 15, "", "Flip Flops für den Urlaub", 0.1, 22, ClothingType.SHOE, "grau", 42, "Kunststoff"));

        listOfAllArticles.add(new Book(7, "Harry Potter and the Order of Pheonix", 23.50, "", "Tolles Buch für Kinder wie Jonas", 0.5, 5, "123456", "Harry Potter and the Order of Pheonix", "J.K. Rowling", 690, "MusterVerlag"));
        listOfAllArticles.add(new Book(8, "Die Räuber", 11, "", "Oida des woa lektüre. goa koan bock", 0.5, 9, "23456", "Die Räuber", "Schiller", 175, "Hamburger Buchverlag"));
        listOfAllArticles.add(new Book(9, "Unter allem liegt die Angst", 19.90, "", "Sehr emotionales Buch welches über Rassismus im Alltag spricht", 0.4, 1, "354567", "Unter allem liegt die Angst", "Matthias Daxer", 200, "FreeVerlag.online"));
    }
    private static void showAllArticles(){
        for(Article a: listOfAllArticles){
            System.out.printf("%50s %8.2f %20s %20s\n", a.getProductName(), a.getProductPrice(), a.getProductBrand(), a.getClass().getSimpleName());
        }
    }
    private static char showLoginMenu(){
        sc = new Scanner(System.in);
        char choice = ' ';
        do {
            System.out.print("Anmelden / Registrieren / Abbrechen [a|r|b] >>> ");
            choice = sc.next().toLowerCase().charAt(0);
        } while (choice != 'a' && choice != 'r' && choice != 'b');
        sc.close();
        return choice;
    }
    private static void registerUser(){
        sc = new Scanner(System.in);
        User registeredUser = new User();
        try{
            rep= new RepositoryOnlineshopDB();

            rep.open();
        System.out.println("Schön, Sie als neuen Nutzer gewonnen zu haben!");

            User u2 = new User(2, "Julia", "Biechl", LocalDate.of(2005, 9, 1), Gender.f, "j.b@gmail.com", "12345678", null, null);
            rep.insertUser(u2, a4);
        System.out.print("Bitte geben Sie ihre Email ein >>> ");
        registeredUser.setEmail(sc.nextLine());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.print("Bitte geben Sie Ihren Vornamen ein >>> ");
        registeredUser.setFirstname(sc.nextLine());

        System.out.print("Bitte geben Sie Ihren Nachnamen ein >>> ");
        registeredUser.setLastname(sc.nextLine());

        System.out.println("Bitte geben Sie Ihren Geburtstag an >>> ");
    }
}
