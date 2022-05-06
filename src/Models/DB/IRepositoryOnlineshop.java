package Models.DB;
import Models.*;

import java.sql.SQLException;
import java.util.List;

public interface IRepositoryOnlineshop {

    void open() throws SQLException;

    void close() throws SQLException;

    void insertUser(User user, Address address) throws SQLException;

    boolean login(User user) throws SQLException;

    boolean updateArticle(Article article) throws SQLException;

    void insertArticle(Article article) throws SQLException;

    List<Article> getAllArticles() throws SQLException;


}
