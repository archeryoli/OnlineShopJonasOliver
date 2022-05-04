package Models.DB;

import Models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean updateArticle(Article article) throws SQLException {

        CallableStatement stmt = this._connection.prepareCall("{call updateArticle(?, ?, ?, ?, ?, ?, ?}");

        stmt.setInt(1, article.getProductId());
        stmt.setString(2, article.getProductName());
        stmt.setDouble(3, article.getProductPrice());
        stmt.setString(4, article.getProductBrand());
        stmt.setString(5, article.getProductDescription());
        stmt.setDouble(6, article.getProductWeight());
        stmt.setInt(7, article.getProductStockCount());

        return stmt.executeUpdate() == 1;
    }

    @Override
    public void insertArticle(Article article) throws SQLException {

        PreparedStatement pStmt = this._connection.prepareStatement("insert into article values(null, ?, ?, ?, ?, ?, ?)");


        pStmt.setString(1, article.getProductName());
        pStmt.setDouble(2, article.getProductPrice());
        pStmt.setString(3, article.getProductBrand());
        pStmt.setString(4, article.getProductDescription());
        pStmt.setDouble(5, article.getProductWeight());
        pStmt.setInt(6, article.getProductStockCount());

        if (article instanceof Clothing){
            insertClothing((Clothing) article);
        }
        else if(article instanceof Electronics){
            insertElectronics((Electronics) article);
        }
        else if(article instanceof Book){
            insertBook((Book) article);
        }



    }

    public void insertClothing(Clothing clothing) throws SQLException {

        PreparedStatement pStmt = this._connection.prepareStatement("insert into clothing values(?, ?, ?, ?, ?)");

        pStmt.setInt(1, getIdfromArticle(clothing));
        pStmt.setInt(2, clothing.getClothingType().ordinal());
        pStmt.setString(3, clothing.getClothingColor());
        pStmt.setInt(4, clothing.getClothingSize());
        pStmt.setString(5, clothing.getClothingFabric());
    }


    public void insertElectronics(Electronics electronics) throws SQLException {

        PreparedStatement pStmt = this._connection.prepareStatement("insert into electronics values(?, ?, ?, ?)");

        pStmt.setInt(1, getIdfromArticle(electronics));
        pStmt.setString(2, electronics.getElectronicsModel());
        pStmt.setDouble(3, electronics.getElectronicsWattage());
        pStmt.setString(4, electronics.getElectronicsDimensions());
    }


    public void insertBook(Book book) throws SQLException {

        PreparedStatement pStmt = this._connection.prepareStatement("insert into book values (?, ?, ?, ?, ?, ?)");

        pStmt.setInt(1, getIdfromArticle(book));


    }

    public int getIdfromArticle(Article article) throws SQLException {

        PreparedStatement pStmt = this._connection.prepareStatement("select ? from article where productName = ?, productPrice = ?, productBrand = ?, productDescription = ?, productWeight = ?, productDtockCount");

        pStmt.setInt(1, article.getProductId());
        pStmt.setString(2, article.getProductName());
        pStmt.setDouble(3, article.getProductPrice());
        pStmt.setString(4, article.getProductBrand());
        pStmt.setString(5, article.getProductDescription());
        pStmt.setDouble(6, article.getProductWeight());
        pStmt.setInt(7, article.getProductStockCount());

        return article.getProductId();
    }

    @Override
    public List<Article> getAllArticles() throws SQLException {
        List<Article> foundArticles = new ArrayList<>();
        PreparedStatement stmt = this._connection.prepareCall("select * from article left join electronics using(articleId) left join clothing using(articleId) left join book using(articleId)");

        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Article a = new Article();
            a.setProductId(rs.getInt("articleId"));
            a.setProductName(rs.getString("productName"));
            a.setProductPrice(rs.getDouble("productPrice"));
            a.setProductBrand(rs.getString("productBrand"));
            a.setProductDescription(rs.getString("productDescription"));
            a.setProductWeight(rs.getDouble("productWeight"));
            a.setProductStockCount(rs.getInt("productStockCount"));
            if(rs.getString("electronicsModel") != null){
                Electronics e = new Electronics(a);

                e.setElectronicsModel(rs.getString("electronicsModel"));
                e.setElectronicsWattage(rs.getDouble("electronicsWattage"));
                e.setElectronicsDimensions(rs.getString("electronicsDimensions"));
                System.out.println("Elek");
            } else if(rs.getString("clothingSize") != null){
                Clothing c = new Clothing(a);
                c.setClothingType(ClothingType.values()[rs.getInt("clothingType")]);
                c.setClothingColor(rs.getString("clothingColor"));
                c.setClothingSize(rs.getInt("clothingSize"));
                c.setClothingFabric(rs.getString("clothingFabric"));
                System.out.println("Cloth");
            } else if(rs.getString("title") != null){
                Book b = new Book(a);
                b.setBookISBNR(rs.getString("bookISBNR"));
                b.setTitle(rs.getString("title"));
                b.setBookAuthor(rs.getString("bookAuthor"));
                b.setBookPages(rs.getInt("bookPages"));
                b.setBookPublisher(rs.getString("bookPublisher"));
                System.out.println("book");

            }
            foundArticles.add(a);
            //asdf.setasdf(result.getDatenTyp("Label"));
        }
        return foundArticles;
    }







}
