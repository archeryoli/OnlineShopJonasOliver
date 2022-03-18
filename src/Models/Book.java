package Models;

public class Book {

    private String _bookISBNR;
    private String _title;
    private String _bookAuthor;
    private int _bookPages;
    private String _bookPublisher;

    public Book(){
        this("", "", "", 0, "");
    }

    public Book(String _bookISBNR, String _title, String _bookAuthor, int _bookPages, String _bookPublisher) {
        this.setBookISBNR(_bookISBNR);
        this.setTitle(_title);
        this.setBookAuthor(_bookAuthor);
        this.setBookPages(_bookPages);
        this.setBookPublisher(_bookPublisher);
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;
        final Book other = (Book) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$_bookISBNR = this.getBookISBNR();
        final Object other$_bookISBNR = other.getBookISBNR();
        if (this$_bookISBNR == null ? other$_bookISBNR != null : !this$_bookISBNR.equals(other$_bookISBNR))
            return false;
        final Object this$_title = this.getTitle();
        final Object other$_title = other.getTitle();
        if (this$_title == null ? other$_title != null : !this$_title.equals(other$_title)) return false;
        final Object this$_bookAuthor = this.getBookAuthor();
        final Object other$_bookAuthor = other.getBookAuthor();
        if (this$_bookAuthor == null ? other$_bookAuthor != null : !this$_bookAuthor.equals(other$_bookAuthor))
            return false;
        final Object this$_bookPages = this.getBookpages();
        final Object other$_bookPages = other.getBookpages();
        if (this$_bookPages == null ? other$_bookPages != null : !this$_bookPages.equals(other$_bookPages))
            return false;
        final Object this$_bookPublisher = this.getBookPublisher();
        final Object other$_bookPublisher = other.getBookPublisher();
        if (this$_bookPublisher == null ? other$_bookPublisher != null : !this$_bookPublisher.equals(other$_bookPublisher))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Book;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $_bookISBNR = this.getBookISBNR();
        result = result * PRIME + ($_bookISBNR == null ? 43 : $_bookISBNR.hashCode());
        final Object $_title = this.getTitle();
        result = result * PRIME + ($_title == null ? 43 : $_title.hashCode());
        final Object $_bookAuthor = this.getBookAuthor();
        result = result * PRIME + ($_bookAuthor == null ? 43 : $_bookAuthor.hashCode());
        final Object $_bookPages = this.getBookpages();
        result = result * PRIME + ($_bookPages == null ? 43 : $_bookPages.hashCode());
        final Object $_bookPublisher = this.getBookPublisher();
        result = result * PRIME + ($_bookPublisher == null ? 43 : $_bookPublisher.hashCode());
        return result;
    }

    public String toString() {
        return "Book(_bookISBNR=" + this.getBookISBNR() + ", _title=" + this.getTitle() + ", _bookAuthor=" + this.getBookAuthor() + ", _bookPages=" + this.getBookpages() + ", _bookPublisher=" + this.getBookPublisher() + ")";
    }
}
