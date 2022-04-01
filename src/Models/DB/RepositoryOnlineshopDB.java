package Models.DB;

import Models.*;
import java.sql.*;

public class RepositoryOnlineshopDB implements IRepositoryOnlineshop{



    //fields
    private String url = "jdbc:mysql://localhost/onlineshop";
    private String user = "root";
    private String pwd = "Samuelx2580";
    private Connection _connection;

    //ctor
    public RepositoryOnlineshopDB() throws ClassNotFoundException {
        // in dieser Zeile wird der Datenbank-Treiber geladen
        Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
        // konnte der MySql-Treiber geladen werden
        if (c != null){
            // ... geben wir eine Meldung aus
            System.out.println("Der MySql-Treiber wurde geladen!");
        }
    }

    @Override
    public void open() throws SQLException {
        this._connection = DriverManager.getConnection(url, user, pwd);
    }

    @Override
    public void close() throws SQLException {
        if ((this._connection != null) && (!this._connection.isClosed()) ){
            // ... wird sie geschlossen
            this._connection.close();
            System.out.println("Verbindung wurde geschlossen!");
        }

    }

    @Override
    public void insertUser(User user, Address address) throws SQLException {

        CallableStatement stmt = this._connection.prepareCall("{call insertUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

        stmt.setString(1, user.getFirstname());
        stmt.setString(2, user.getLastname());

        Date date = Date.valueOf(user.getBirthdate());
        stmt.setDate(3, date);

        String gender = user.getGender() + "";
        stmt.setString(4, gender);
        stmt.setString(5, user.getEmail());
        stmt.setString(6, user.getPassword());
        stmt.setString(7, address.getState());
        stmt.setString(8, address.getPostalcode());
        stmt.setString(9, address.getCity());
        stmt.setString(10, address.getStreet());
        stmt.setString(11, address.getStreetNr());

        ResultSet result = stmt.executeQuery();

    }

    @Override
    public boolean login(User user) throws SQLException {

        PreparedStatement stmt = this._connection.prepareStatement("select login(?, sha2(?, 256)) as login from user limit 1;");

        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPassword());

        ResultSet result = stmt.executeQuery();
        result.next();

        boolean l = result.getBoolean("login");

        return l;
    }


}
