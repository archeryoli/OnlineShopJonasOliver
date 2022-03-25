package Models;

public abstract class Article {
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Article)) return false;
        final Article other = (Article) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getProductId() != other.getProductId()) return false;
        final Object this$_productName = this.getProductName();
        final Object other$_productName = other.getProductName();
        if (this$_productName == null ? other$_productName != null : !this$_productName.equals(other$_productName))
            return false;
        if (Double.compare(this.getProductPrice(), other.getProductPrice()) != 0) return false;
        final Object this$_productBrand = this.getProductBrand();
        final Object other$_productBrand = other.getProductBrand();
        if (this$_productBrand == null ? other$_productBrand != null : !this$_productBrand.equals(other$_productBrand))
            return false;
        final Object this$_productDescription = this.getProductDescription();
        final Object other$_productDescription = other.getProductDescription();
        if (this$_productDescription == null ? other$_productDescription != null : !this$_productDescription.equals(other$_productDescription))
            return false;
        if (Double.compare(this.getProductWeight(), other.getProductWeight()) != 0) return false;
        if (this.getProductStockCount() != other.getProductStockCount()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getProductId();
        final Object $_productName = this.getProductName();
        result = result * PRIME + ($_productName == null ? 43 : $_productName.hashCode());
        final long $_productPrice = Double.doubleToLongBits(this.getProductPrice());
        result = result * PRIME + (int) ($_productPrice >>> 32 ^ $_productPrice);
        final Object $_productBrand = this.getProductBrand();
        result = result * PRIME + ($_productBrand == null ? 43 : $_productBrand.hashCode());
        final Object $_productDescription = this.getProductDescription();
        result = result * PRIME + ($_productDescription == null ? 43 : $_productDescription.hashCode());
        final long $_productWeight = Double.doubleToLongBits(this.getProductWeight());
        result = result * PRIME + (int) ($_productWeight >>> 32 ^ $_productWeight);
        result = result * PRIME + this.getProductStockCount();
        return result;
    }

    public String toString() {
        return "Article(_productId=" + this._productId + ", _productName=" + this._productName + ", _productPrice=" + this._productPrice + ", _productBrand=" + this._productBrand + ", _productDescription=" + this._productDescription + ", _productWeight=" + this._productWeight + ", _productStockCount=" + this._productStockCount + ")";
    }
}
