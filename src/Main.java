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

    public static void main(String[] args) {
        // admin Email: admin@admin.com
        // admin Passwort: root
        String choice = "";

        getAllArticles();
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
                    break;
                case 'e':
                    if(currentUser.getIsAdmin()){
                        editArticle();
                    }
                    break;
            }

        }while (choice.charAt(0) != 'q');
        System.out.println("Danke für Ihren Besuch");
    }

    private static void editArticle() {
        int idOfProduct = 0;
        do{
            System.out.print("Bitte geben Sie die ID Ihres Artikels an oder abbrechen [q] >>> ");
            String v = sc.nextLine();

            if(v.toLowerCase().charAt(0) == 'q' ){
                return;
            }
            if(v.matches("-?\\d+")){
                if(checkIfValidId(Integer.parseInt(v))){
                    idOfProduct = Integer.parseInt(v);
                } else{
                    System.out.println("Ungültige ID-Nummer!");
                }
            } else {
                System.out.println("Bitte geben Sie eine Zahl ein!");
            }
        } while (!(idOfProduct > 0));
        Article currentArticle = null;
        for(Article a: listOfAllArticles){
            if(idOfProduct == a.getProductId()){
                currentArticle = a;
                break;
            }
        }
        String choice;

        System.out.print("Was soll der neue Name des Produkts sein, oder überspringen [b] >>> ");
        choice = sc.nextLine();
        currentArticle.setProductName(choice.trim().equals("b") ? currentArticle.getProductName() : choice);

        System.out.print("Was soll der neue Preis des Produkts sein, oder überspringen [b] >>> ");
        choice = sc.nextLine();
        currentArticle.setProductPrice(choice.trim().equals("b") ? currentArticle.getProductPrice() : Double.parseDouble(choice));

        System.out.print("Was soll die neue Marke des Produkts sein, oder überspringen [b] >>> ");
        choice = sc.nextLine();
        currentArticle.setProductBrand(choice.trim().equals("b") ? currentArticle.getProductBrand() : choice);

        System.out.print("Was soll die neue Beschreibung des Produkts sein, oder überspringen [b] >>> ");
        choice = sc.nextLine();
        currentArticle.setProductDescription(choice.trim().equals("b") ? currentArticle.getProductDescription() : choice);

        System.out.print("Was soll das neue Gewicht des Produkts sein, oder überspringen [b] >>> ");
        choice = sc.nextLine();
        currentArticle.setProductWeight(choice.trim().equals("b") ? currentArticle.getProductWeight() : Double.parseDouble(choice));

        System.out.print("Was soll die neue Stückzahl des Produkts sein, oder überspringen [b] >>> ");
        choice = sc.nextLine();
        currentArticle.setProductStockCount(choice.trim().equals("b") ? currentArticle.getProductStockCount() : Integer.parseInt(choice));

        try{
            rep = new RepositoryOnlineshopDB();
            rep.open();
            rep.updateArticle(currentArticle);
            rep.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void searchMenu() {
        String choice = "";
        System.out.println("Suche nach Name [n]");
        System.out.println("Suche nach Marke [m]");
        System.out.println("Suche nach Typ [t]");
        System.out.println("Suche nach Preisgrenzen [p]");
        System.out.println("Details eines Produkts anzeigen [i]");
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
            case 't':
                searchByType();
                break;
            case 'i':
                productDetail();
                break;
        }
    }

    private static void productDetail() {
        int idOfProduct = 0;
        do{
            System.out.print("Bitte geben Sie die ID Ihres Artikels an oder abbrechen [q] >>> ");
            String v = sc.nextLine();

            if(v.toLowerCase().charAt(0) == 'q' ){
                return;
            }
            if(v.matches("-?\\d+")){
                if(checkIfValidId(Integer.parseInt(v))){
                    idOfProduct = Integer.parseInt(v);
                } else{
                    System.out.println("Ungültige ID-Nummer!");
                }
            } else {
                System.out.println("Bitte geben Sie eine Zahl ein!");
            }
        } while (!(idOfProduct > 0));

        Table t = new Table(8, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Beschreibung");
        t.addCell("Preis");
        t.addCell("Marke");
        t.addCell("Typ");
        t.addCell("Gewicht");
        t.addCell("Lagermenge");

        for(Article a: listOfAllArticles){
            if(idOfProduct == a.getProductId()){
                t.addCell(String.valueOf(a.getProductId()));
                t.addCell(a.getProductName());
                t.addCell(a.getProductDescription());
                t.addCell(String.format("%.2f", a.getProductPrice()));
                t.addCell(a.getProductBrand());
                t.addCell(a.getClass().getSimpleName());
                t.addCell(String.valueOf(a.getProductWeight()));
                t.addCell(String.valueOf(a.getProductStockCount()));
            }
        }
        System.out.println(t.render());
    }

    private static boolean checkIfValidId(int idToCheck){
        for(Article a: listOfAllArticles){
            if(a.getProductId() == idToCheck){
                return true;
            }
        }
        return false;
    }

    private static boolean checkIfValidIdInBasket(int idToCheck){
        for (Map.Entry<Article, Integer> entry: currentUser.getBasket().getBasketHashMap().entrySet()){
            if (entry.getKey().getProductId() == idToCheck){
                return true;
            }
        }
        return false;
    }

    private static void searchByType() {
        System.out.print("Bitte geben Sie den zu suchenden Typ ein >>> ");
        String typeToLookFor = sc.nextLine();

        Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Preis");
        t.addCell("Marke");
        t.addCell("Typ");
        int size = 0;
        for(Article a: listOfAllArticles){
            if(a.getClass().getSimpleName().toLowerCase().contains(typeToLookFor.toLowerCase())){
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
                 if(checkIfValidId(Integer.parseInt(v))){
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
                 if(Integer.parseInt(v) > 0){
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
        // Wenn der Basket leer ist
        if(currentUser.getBasket().equals(new Basket()) || currentUser.getBasket() == null){
            System.out.println("Hier gibt es nichts zu sehen ...\n");
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

        System.out.print("\nKaufen | ändern | abbrechen [k|e|q] >>> ");
        String input = sc.nextLine();
        if(input.toLowerCase().charAt(0) == 'k'){
            // Wenn es keine Probleme gibt
            if(checkStockAtCheckOut()){
                System.out.println("Hier ist Ihre Rechnung:");
                buyArticlesInBasket();
            } else {
                System.out.println("Bitte kümmern Sie sich über Ihren Warenkorb!");
            }
        } else if(input.toLowerCase().charAt(0) == 'e'){
            editBasket();
        }
    }

    private static void buyArticlesInBasket() {
        Table t = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        t.addCell("Stückanzahl");
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Preis pro Stück");
        t.addCell("Marke");
        t.addCell("Typ");
        t.addCell("GesamtPreis");
        double sumPrice = 0;

        for(Map.Entry<Article, Integer> entry: currentUser.getBasket().getBasketHashMap().entrySet()){
            t.addCell(String.valueOf(entry.getValue()));
            t.addCell(String.valueOf(entry.getKey().getProductId()));
            t.addCell(entry.getKey().getProductName());
            t.addCell(String.format("%.2f", entry.getKey().getProductPrice()));
            t.addCell(entry.getKey().getProductBrand());
            t.addCell(entry.getKey().getClass().getSimpleName());
            t.addCell(String.format("%.2f", entry.getKey().getProductPrice() * entry.getValue()));
            sumPrice += entry.getKey().getProductPrice() * entry.getValue();
        }
        System.out.println(t.render());
        System.out.printf("Ihre Rechnung macht %.2f aus.\n\n", sumPrice);

        try{
            rep = new RepositoryOnlineshopDB();
            rep.open();

            for(Map.Entry<Article, Integer> entry: currentUser.getBasket().getBasketHashMap().entrySet()){
                Article updatedArticle = entry.getKey();
                updatedArticle.setProductStockCount(updatedArticle.getProductStockCount() - entry.getValue());
                rep.updateArticle(updatedArticle);
            }

            rep.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        getAllArticles();
        JsonWriter.writeObjectToJson(null, Path.of("basket.json"));
        currentUser.setBasket(new Basket());
    }

    private static void showShopMenu() {

        System.out.println("Wie wollen Sie fortfahren?");
        System.out.println("Alle Artikel anzeigen [a]");
        System.out.println("Bestimmten Artikel suchen [s]");
        System.out.println("Einen Artikel dem Warenkorb hinzufügen [h]");
        System.out.println("Warenkorb anzeigen [w]");
        if(currentUser.getIsAdmin()){
            System.out.println("Artikel ändern [e]");
        }
        System.out.println("Abbrechen [q]");
        System.out.print(">>> ");
    }

    private static void getAllArticles(){
        listOfAllArticles = new ArrayList<>();
        try{
            rep = new RepositoryOnlineshopDB();
            rep.open();
            for(Article a: rep.getAllArticles()){
                listOfAllArticles.add(a);
            }
            rep.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
            email = sc.nextLine();
            u.setEmail(email);
            if(email == "q"){
                break;
            }
            System.out.print("Bitte Passwort eingeben >>> ");
            password = sc.nextLine();
            u.setPassword(password);
            if(u.getEmail().equals(Admin.getUserName()) && u.getPassword().equals(Admin.getPassword())){
                u.setIsAdmin(true);
                break;
            }
            try {
                rep = new RepositoryOnlineshopDB();
                rep.open();
                success = rep.login(u);
                rep.close();
            }
            catch (SQLException | ClassNotFoundException e){
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
        System.out.print("Bitte geben Sie den Staat Ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setState(input);

        System.out.print("Bitte geben Sie die Postleitzahl ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setPostalcode(input);

        System.out.print("Bitte geben Sie den Ort ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setCity(input);

        System.out.print("Bitte geben Sie die Straße Ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setStreet(input);

        System.out.print("Bitte geben Sie die Hausnummer Ihrer Adresse an >>> ");
        input = sc.nextLine();
        addressToAdd.setStreetNr(input);

        return addressToAdd;
    }

    private static boolean checkStockAtCheckOut(){

        List<Article> failedArticles = new ArrayList<>();
        boolean success = true;
        for(Map.Entry<Article, Integer> entry: currentUser.getBasket().getBasketHashMap().entrySet()){
            for (Article a : listOfAllArticles){
                if (a.getProductId() == entry.getKey().getProductId()){
                    if (a.getProductStockCount() < entry.getValue()){
                        failedArticles.add(a);
                        success = false;
                    }
                }
            }
        }
        System.out.println("Es wurde ein Fehler in Ihrer bestellung gefunden: ");
        Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        t.addCell("ID-Nummer");
        t.addCell("Name");
        t.addCell("Preis");
        t.addCell("Marke");
        t.addCell("Typ");
        for(Article a: failedArticles){
            t.addCell(String.valueOf(a.getProductId()));
            t.addCell(a.getProductName());
            t.addCell(String.format("%.2f", a.getProductPrice()));
            t.addCell(a.getProductBrand());
            t.addCell(a.getClass().getSimpleName());
        }
        System.out.println(t.render());
        return success;
    }

    private static void editBasket(){
        System.out.print("ID von Artikel den sie Bearbeiten möchten >>> ");
        int idOfProduct = 0;
        do {
            System.out.print("Bitte geben Sie die ID-Nummer Ihres Artikels an oder abbrechen [q] >>> ");
            String v = sc.nextLine();
            if(v.toLowerCase().charAt(0) == 'q' ){
                return;
            }
            if(v.matches("-?\\d+")){
                if(checkIfValidIdInBasket(Integer.parseInt(v))){
                    idOfProduct = Integer.parseInt(v);
                } else{
                    System.out.println("Ungültige ID-Nummer!");
                }
            } else {
                System.out.println("Bitte geben Sie eine Zahl ein!");
            }
        } while (!(idOfProduct > 0));
        Article foundArticle = null;
        for (Map.Entry<Article, Integer> entry: currentUser.getBasket().getBasketHashMap().entrySet()){
            if(entry.getKey().getProductId() == idOfProduct) {
                foundArticle = entry.getKey();
                break;
            }
        }
        String choice = "";
        System.out.println("Artikel löschen [l]");
        System.out.println("Anzahl ändern [c]");
        System.out.println("Abbrechen [q]");
        System.out.print(">>> ");

        choice = sc.nextLine().toLowerCase();

        switch (choice.charAt(0)) {
            case 'q':
                break;

            case 'l':
                currentUser.getBasket().removeBasketHashMapEntry(foundArticle);
                JsonWriter.writeObjectToJson(currentUser.getBasket(), Path.of("basket.json"));

                break;

            case 'c':
                int newBasektCount = 0;
                do {
                    System.out.print("Neue Anzahl eingeben >>> ");
                    String v = sc.nextLine();
                    if (v.matches("-?\\d+")) {
                        if (checkIfValidIdInBasket(Integer.parseInt(v))) {
                            newBasektCount = Integer.parseInt(v);
                        } else {
                            System.out.println("Ungültige Id!");
                        }
                    } else {
                        System.out.println("Bitte geben Sie eine Zahl ein!");
                    }
                } while (!(idOfProduct > 0));
                currentUser.getBasket().updateHashMapEntry(foundArticle, newBasektCount);

                JsonWriter.writeObjectToJson(currentUser.getBasket(), Path.of("basket.json"));
                break;
        }
    }
}



