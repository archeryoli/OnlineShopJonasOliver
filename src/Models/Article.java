package Models;

public class Article {
    private int _productId;
    private String _productName;
    private double _productPrice;
    private String _productBrand;
    private String _productDescription;
    private double _productWeight;
    private int _productStockCount;

    public Article() {
        this(0, "", 0.0, "", "", 0.0, 0);
    }
    public Article(Article a){
        this(a.getProductId(), a.getProductName(), a.getProductPrice(), a.getProductBrand(), a.getProductDescription(), a.getProductWeight(), a.getProductStockCount());
    }
    public Article(int productId, String productName, double productPrice, String productBrand, String productDescription, double productWeight, int productStockCount){
        this.setProductId(productId);
        this.setProductName(productName);
        this.setProductPrice(productPrice);
        this.setProductBrand(productBrand);
        this.setProductDescription(productDescription);
        this.setProductWeight(productWeight);
        this.setProductStockCount(productStockCount);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Article;
    }

    public int getProductId() {
        return this._productId;
    }

    public String getProductName() {
        return this._productName;
    }

    public double getProductPrice() {
        return this._productPrice;
    }

    public String getProductBrand() {
        return this._productBrand;
    }

    public String getProductDescription() {
        return this._productDescription;
    }

    public double getProductWeight() {
        return this._productWeight;
    }

    public int getProductStockCount() {
        return this._productStockCount;
    }

    public void setProductId(int _productId) {
        if(_productId >= 0){
            this._productId = _productId;
        }

    }

    public void setProductName(String _productName) {
        if(!(_productName.isEmpty() || _productName == null)){
            this._productName = _productName;
        }
    }

    public void setProductPrice(double _productPrice) {
        if(_productPrice > 0){
            this._productPrice = _productPrice;
        }
    }

    public void setProductBrand(String _productBrand) {
        if(!(_productBrand.isEmpty() || _productBrand == null)){
            this._productBrand = _productBrand;
        }
    }

    public void setProductDescription(String _productDescription) {
        if(!(_productDescription.isEmpty() || _productDescription == null)){
            this._productDescription = _productDescription;
        }
    }

    public void setProductWeight(double _productWeight) {
        if(_productWeight > 0){
            this._productWeight = _productWeight;
        }
    }

    public void setProductStockCount(int _productStockCount) {
        if(_productStockCount >= 0){
            this._productStockCount = _productStockCount;
        }
    }

    public String toString() {
        return "Article(_productId=" + this._productId + ", _productName=" + this._productName + ", _productPrice=" + this._productPrice + ", _productBrand=" + this._productBrand + ", _productDescription=" + this._productDescription + ", _productWeight=" + this._productWeight + ", _productStockCount=" + this._productStockCount + ")";
    }
}
