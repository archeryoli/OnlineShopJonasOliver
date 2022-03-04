import Models.Article;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Article article = new Article();
        article.setProductId(1);
        System.out.println(article.getProductId());
    }
}
