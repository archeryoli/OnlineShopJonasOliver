import Models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    }
}
