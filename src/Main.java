import Json.JsonReader;
import Json.JsonWriter;
import Models.*;
import Models.DB.IRepositoryOnlineshop;
import Models.DB.RepositoryOnlineshopDB;

import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import org.nocrala.tools.texttablefmt.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Article> listOfAllArticles = new ArrayList<>();
    static IRepositoryOnlineshop rep = null;
    static User currentUser = new User();
    private static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) {

        String choice = "";

        init();
        System.out.println("Olineshop von Jonas und Oliver");
        System.out.println("==============================");


        switch (showLoginMenu()){
            case 'a':
                currentUser = login();
                currentUser.setBasket(JsonReader.getObjectFromFile(Path.of("basket.json")));
                break;
            case 'r':
                currentUser = registerUser();
                System.out.println(currentUser);
                break;
            case 'b':
                System.out.println("Danke für Ihren Besuch");
                System.exit(0);
        }
        System.out.println("Schön das Sie hier sind!\n");
        System.out.println("========================\n");

        do {
            showShopMenu();
            choice = sc.nextLine().toLowerCase();
            switch (choice.charAt(0)){
                case 'q':
                    break;
                case 'a':
                    showAllArticles();
                    break;
                case 'w':
                    showBasket(currentUser);
                    break;
                case 'h':
                    addProductMenu();
                    break;
                case 's':
                    searchMenu();
            }

        }while (choice.charAt(0) != 'q');
        System.out.println("Danke für Ihren Besuch");
    }

    private static void searchMenu() {
        String choice = "";
        System.out.println("Suche nach Name [n]");
        System.out.println("Suche nach Marke [m]");
        System.out.println("Suche nach Typ [t]");
        System.out.println("Suche nach Preisgrenzen [p]");
        System.out.println("Zurück zum Hauptmenü [q]");
        System.out.print(">>> ");

        choice = sc.nextLine().toLowerCase();

        switch (choice.charAt(0)){
            case 'q':
                break;
            case 'n':
                searchByName();
                break;
            case 'm':
                searchByBrand();
                break;
            case 'p':
                searchByPriceRange();
                break;
        }
    }

    private static void searchByPriceRange() {
        double lowerPrice = 0, upperPrice = 0;
        String input;
        boolean success = false;
        do {
            System.out.print("Bitte geben Sie Ihren Preisbereich ein [tief-hoch] >>> ");
            input = sc.nextLine();
            String[] splitString = input.split("-");
            if(splitString.length == 2){

                if(!(splitString[0].matches("-?\\d+") && splitString[1].matches("-?\\d+"))) {
                    System.out.println("Bitte gib Zahlen an!");
                    // Springt zum loop-Anfang
                    continue;
                }

            }else {
                System.out.println("Falsches Eingabe-Format!");
                continue;
            }
            lowerPrice = Double.parseDouble(splitString[0]);
            upperPrice = Double.parseDouble(splitString[1]);
            success = true;
        } while(!success);

        // Ausgabe
        Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Preis");
        t.addCell("Marke");
        t.addCell("Typ");
        int size = 0;
        for(Article a: listOfAllArticles){
            if(a.getProductPrice() >= lowerPrice && a.getProductPrice() <= upperPrice){
                t.addCell(String.valueOf(a.getProductId()));
                t.addCell(a.getProductName());
                t.addCell(String.format("%.2f", a.getProductPrice()));
                t.addCell(a.getProductBrand());
                t.addCell(a.getClass().getSimpleName());
                size++;
            }
        }
        if(size == 0){
            System.out.println("Es wurden keine Produkte gefunden!");
        } else {
            System.out.println(t.render());
        }
    }

    private static void searchByBrand() {
        System.out.print("Bitte geben Sie die zu suchende Marke ein >>> ");
        String brandToLookFor = sc.nextLine();
        List<Article> foundArticles = new ArrayList<>();

        Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Preis");
        t.addCell("Marke");
        t.addCell("Typ");
        int size = 0;
        for(Article a: listOfAllArticles){
            if(a.getProductBrand() != null && a.getProductBrand().toLowerCase().contains(brandToLookFor.toLowerCase())){
                t.addCell(String.valueOf(a.getProductId()));
                t.addCell(a.getProductName());
                t.addCell(String.format("%.2f", a.getProductPrice()));
                t.addCell(a.getProductBrand());
                t.addCell(a.getClass().getSimpleName());
                size++;
            }
        }
        if(size == 0){
            System.out.println("Es wurden keine Produkte gefunden!");
        } else {
            System.out.println(t.render());
        }
    }

    private static void searchByName() {
        System.out.print("Bitte geben Sie den zu suchenden Begriff ein >>> ");
        String nameToLookFor = sc.nextLine();
        List<Article> foundArticles = new ArrayList<>();

        Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Preis");
        t.addCell("Marke");
        t.addCell("Typ");
        int size = 0;
        for(Article a: listOfAllArticles){
            if(a.getProductName().toLowerCase().contains(nameToLookFor.toLowerCase())){
                t.addCell(String.valueOf(a.getProductId()));
                t.addCell(a.getProductName());
                t.addCell(String.format("%.2f", a.getProductPrice()));
                t.addCell(a.getProductBrand());
                t.addCell(a.getClass().getSimpleName());
                size++;
            }
        }
        if(size == 0){
            System.out.println("Es wurden keine Produkte gefunden!");
        } else {
            System.out.println(t.render());
        }
    }

    private static void addProductMenu() {
        int idOfProduct = 0;
        int amountOfProduct = 0;
        do {
            System.out.print("Bitte geben Sie die ID-Nummer Ihres Artikels an oder abbrechen [q] >>> ");
            String v = sc.nextLine();
            if(v.toLowerCase().charAt(0) == 'q' ){
                return;
            }
            if(v.matches("-?\\d+")){
                 if(Integer.parseInt(v)<listOfAllArticles.size()){
                     idOfProduct = Integer.parseInt(v);
                 } else{
                     System.out.println("Ungültige ID-Nummer!");
                 }
            } else {
                System.out.println("Bitte geben Sie eine Zahl ein!");
            }
        } while (!(idOfProduct > 0));

        do {
            System.out.print("Bitte geben Sie die Anzahl der zu bestellenden Artikel an oder abbrechen [q] >>> ");
            String v = sc.nextLine();
            if(v.toLowerCase().charAt(0) == 'q' ){
                return;
            }
            if(v.matches("-?\\d+")){
                 if(Integer.parseInt(v) > 0 ){
                     amountOfProduct = Integer.parseInt(v);
                 } else{
                     System.out.println("Ungültige Anzahl!");
                 }
            } else {
                System.out.println("Bitte geben Sie eine Zahl ein!");
            }
        } while (!(amountOfProduct > 0));


        Article articleToBeAdded = null;
        for(Article a: listOfAllArticles){
            if(a.getProductId() == idOfProduct){
                articleToBeAdded = a;
            }
        }
        currentUser.getBasket().addBasketHashMapEntry(articleToBeAdded, amountOfProduct);
        JsonWriter.writeObjectToJson(currentUser.getBasket(), Path.of("basket.json"));

        System.out.println();
    }

    private static void showBasket(User currentUser) {
        if(currentUser.getBasket() == null){
            System.out.println("Hier gibt es nichts zu sehen ...");
            return;
        }
        Table t = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        t.addCell("Stückanzahl");
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Preis");
        t.addCell("Marke");
        t.addCell("Typ");
        for(Map.Entry<Article, Integer> entry: currentUser.getBasket().getBasketHashMap().entrySet()){
            t.addCell(String.valueOf(entry.getValue()));
            t.addCell(String.valueOf(entry.getKey().getProductId()));
            t.addCell(entry.getKey().getProductName());
            t.addCell(String.format("%.2f", entry.getKey().getProductPrice()));
            t.addCell(entry.getKey().getProductBrand());
            t.addCell(entry.getKey().getClass().getSimpleName());
        }
        System.out.println(t.render());
    }

    private static void showShopMenu() {

        System.out.println("Wie wollen Sie fortfahren?");
        System.out.println("Alle Artikel anzeigen [a]");
        System.out.println("Bestimmten Artikel suchen [s]");
        System.out.println("Einen Artikel dem Warenkorb hinzufügen [h]");
        System.out.println("Warenkorb anzeigen [w]");
        System.out.println("Benutzerdaten ändern [b]");
        System.out.println("Abbrechen [q]");
        System.out.print(">>> ");
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
        Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Preis");
        t.addCell("Marke");
        t.addCell("Typ");
        for(Article a: listOfAllArticles){
            t.addCell(String.valueOf(a.getProductId()));
            t.addCell(a.getProductName());
            t.addCell(String.format("%.2f", a.getProductPrice()));
            t.addCell(a.getProductBrand());
            t.addCell(a.getClass().getSimpleName());
        }
        System.out.println(t.render());
    }

    private static char showLoginMenu(){
        sc = new Scanner(System.in);
        char choice = ' ';
        do {
            System.out.print("Anmelden / Registrieren / Abbrechen [a|r|b] >>> ");
            choice = sc.nextLine().toLowerCase().charAt(0);
        } while (choice != 'a' && choice != 'r' && choice != 'b');
        return choice;
    }

    private static User registerUser() {
        User registeredUser = new User();
        String input = "";

        System.out.println("Schön, Sie als neuen Nutzer gewonnen zu haben!");

        System.out.print("Bitte geben Sie ihre Email ein >>> ");
        sc.nextLine();
        input = sc.nextLine();
        registeredUser.setEmail(input);

        System.out.print("Bitte geben Sie Ihren Vornamen ein >>> ");
        input = sc.nextLine();
        registeredUser.setFirstname(input);

        System.out.print("Bitte geben Sie Ihren Nachnamen ein >>> ");
        input = sc.nextLine();
        registeredUser.setLastname(input);

        System.out.print("Bitte geben Sie Ihren Geburtstag an [yyyy-mm-dd]>>> ");
        String date = sc.nextLine();
        registeredUser.setBirthdate(LocalDate.of(Integer.parseInt(date.split("-")[0]),Integer.parseInt(date.split("-")[1]), Integer.parseInt(date.split("-")[2])));

        Gender g;
        System.out.print("Bitte geben Sie Ihr Geschlecht an [m|w|o] >>> ");
        input = sc.nextLine();
        switch (input.toLowerCase().charAt(0)){
            case 'm':
                g = Gender.m;
                break;
            case 'w':
                g = Gender.f;
                break;
            default:
                g = Gender.o;
                break;
        }
        registeredUser.setGender(g);

        System.out.print("Bitte geben Sie nun Ihr Password ein >>> ");
        input = sc.nextLine();
        registeredUser.setPassword(input);

        System.out.println("Danke für Ihre Registrierung!");

        System.out.print("Wollen Sie bereits eine Adresse hinzufügen [j|n] >>> ");
        char choice = ' ';
        do {
            System.out.print("Wollen Sie eine Adresse hinzufügen [j|n] >>> ");
            choice = sc.nextLine().toLowerCase().charAt(0);
        } while (choice != 'j' && choice != 'n');

        // falls nein gewählt wird wird der user einfach hinzugefügt
        Address insertAddress = new Address();
        if (choice == 'j'){
            insertAddress = addAddress();
        }


        try{
            rep = new RepositoryOnlineshopDB();
            rep.open();
            rep.insertUser(registeredUser, insertAddress);
            rep.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Sie wurden erfolgreich regestriert!");
        return registeredUser;
    }

    public static User login() {
        boolean success = false;
        User u = new User();
        do{

            String email, password;


            System.out.print("Bitte EMail angeben oder abbrechen[q] >>> ");
            email = reader.nextLine();
            u.setEmail(email);
            if(email == "q"){
                break;
            }
            System.out.println("Bitte Passwort eingeben >>> ");
            password = reader.nextLine();
            u.setPassword(password);
        try {
            rep.open();
            success = rep.login(u);
            rep.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        if (!success){
            System.out.println("Benutzername oder Passwort stimmen nicht überein");
        }
        } while(!success);
        return u;

    }



    private static Address addAddress() {
        String input = "";
        Address addressToAdd = new Address();
        System.out.println("Bitte geben Sie den Staat Ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setState(input);

        System.out.println("Bitte geben Sie die Postleitzahl ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setPostalcode(input);

        System.out.println("Bitte geben Sie den Ort ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setCity(input);

        System.out.println("Bitte geben Sie die Straße Ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setStreet(input);

        System.out.println("Bitte geben Sie die Hausnummer Ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setStreetNr(input);

        return addressToAdd;
    }
}
