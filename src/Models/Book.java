package Models;

public class Book extends Article{

    private String _bookISBNR;
    private String _title;
    private String _bookAuthor;
    private int _bookPages;
    private String _bookPublisher;

    public Book(){
        this(0, "", 0.0, "", "", 0.0, 0, "", "", "", 0, "");
    }

    public Book(int productId, String productName, double productPrice, String productBrand, String productDescription, double productWeight, int productStockCount,
                String _bookISBNR, String _title, String _bookAuthor, int _bookPages, String _bookPublisher) {
        super(productId, productName, productPrice, productBrand, productDescription, productWeight, productStockCount);
        this.setBookISBNR(_bookISBNR);
        this.setTitle(_title);
        this.setBookAuthor(_bookAuthor);
        this.setBookPages(_bookPages);
        this.setBookPublisher(_bookPublisher);
    }

    public Book(Article a) {
        super(a);
    }


    public String getBookISBNR() {
        return this._bookISBNR;
    }

    public String getTitle() {
        return this._title;
    }

    public String getBookAuthor() {
        return this._bookAuthor;
    }

    public int getBookpages() {
        return this._bookPages;
    }

    public String getBookPublisher() {
        return this._bookPublisher;
    }

    public void setBookISBNR(String _bookISBNR) {
        if(!(_bookISBNR.isEmpty())) {
            this._bookISBNR = _bookISBNR;
        }
    }

    public void setTitle(String _title) {
        if(!(_title.isEmpty())) {
            this._title = _title;
        }
    }

    public void setBookAuthor(String _bookAuthor) {
        if(!(_bookAuthor.isEmpty())) {
            this._bookAuthor = _bookAuthor;
        }
    }

    public void setBookPages(int _bookPages) {
        if(_bookPages > 0) {
            this._bookPages = _bookPages;
        }
    }

    public void setBookPublisher(String _bookPublisher) {
        if(!(_bookPublisher.isEmpty())) {
            this._bookPublisher = _bookPublisher;
        }
    }

    public String toString() {
        return "Book(" + super.toString() + "_bookISBNR=" + this.getBookISBNR() + ", _title=" + this.getTitle() + ", _bookAuthor=" + this.getBookAuthor() + ", _bookPages=" + this.getBookpages() + ", _bookPublisher=" + this.getBookPublisher() + ")";
    }
}
