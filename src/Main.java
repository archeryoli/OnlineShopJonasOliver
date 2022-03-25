import Json.JsonReader;
import Json.JsonWriter;
import Models.*;
import Models.DB.IRepositoryOnlineshop;
import Models.DB.RepositoryOnlineshopDB;


import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        User u1 = new User(1, "Jonas", "Kirchmair", LocalDate.of(2005, 05, 06), Gender.m, "jonas1.kirchmair@gmail.com", "hallo1234", null, null);
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


        JsonWriter.writeObjectToJson(u1, Path.of("u1.json"));


        IRepositoryOnlineshop rep = null;

        try{
            rep= new RepositoryOnlineshopDB();

            rep.open();

            User u2 = new User(2, "Julia", "Biechl", LocalDate.of(2005, 9, 1), Gender.f, "j.b@gmail.com", "12345678", null, null);
            rep.insertUser(u2, a4);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}
