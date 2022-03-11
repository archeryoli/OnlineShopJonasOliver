package Models;

public class Clothing extends Article{
    private ClothingType _clothingType;
    private String _clothingColor;
    private int _clothingSize;
    private String _clothingFabric;

    public Clothing() {
        this(0, "", 0.0, "", "", 0.0, 0, ClothingType.OTHER, "", 0, "");
    }

    public Clothing(int productId, String productName, double productPrice, String productBrand, String productDescription, double productWeight, int productStockCount,
                    ClothingType clothingType, String clothingColor, int clothingSize, String clothingFabric){
        super(productId, productName, productPrice, productBrand, productDescription, productWeight, productStockCount);
        this.setClothingType(clothingType);
        this.setClothingColor(clothingColor);
        this.setClothingSize(clothingSize);
        this.setClothingFabric(clothingFabric);
    }

    public ClothingType getClothingType() {
        return this._clothingType;
    }

    public String getClothingColor() {
        return this._clothingColor;
    }

    public int getClothingSize() {
        return this._clothingSize;
    }

    public String getClothingFabric() {
        return this._clothingFabric;
    }

    public void setClothingType(ClothingType _clothingType) {
        if(_clothingType != null){
            this._clothingType = _clothingType;
        }
    }

    public void setClothingColor(String _clothingColor) {
        if(!_clothingColor.isEmpty()){
            this._clothingColor = _clothingColor;
        }
    }

    public void setClothingSize(int _clothingSize) {
        if(_clothingSize > 0){
            this._clothingSize = _clothingSize;
        }
    }

    public void setClothingFabric(String _clothingFabric) {
        if(!_clothingFabric.isEmpty()){
            this._clothingFabric = _clothingFabric;
        }
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Clothing)) return false;
        final Clothing other = (Clothing) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$_clothingType = this.getClothingType();
        final Object other$_clothingType = other.getClothingType();
        if (this$_clothingType == null ? other$_clothingType != null : !this$_clothingType.equals(other$_clothingType))
            return false;
        final Object this$_clothingColor = this.getClothingColor();
        final Object other$_clothingColor = other.getClothingColor();
        if (this$_clothingColor == null ? other$_clothingColor != null : !this$_clothingColor.equals(other$_clothingColor))
            return false;
        if (this.getClothingSize() != other.getClothingSize()) return false;
        final Object this$_clothingFabric = this.getClothingFabric();
        final Object other$_clothingFabric = other.getClothingFabric();
        if (this$_clothingFabric == null ? other$_clothingFabric != null : !this$_clothingFabric.equals(other$_clothingFabric))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Clothing;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $_clothingType = this.getClothingType();
        result = result * PRIME + ($_clothingType == null ? 43 : $_clothingType.hashCode());
        final Object $_clothingColor = this.getClothingColor();
        result = result * PRIME + ($_clothingColor == null ? 43 : $_clothingColor.hashCode());
        result = result * PRIME + this.getClothingSize();
        final Object $_clothingFabric = this.getClothingFabric();
        result = result * PRIME + ($_clothingFabric == null ? 43 : $_clothingFabric.hashCode());
        return result;
    }

    public String toString() {
        return "Clothing(" + super.toString() + "_clothingType=" + this.getClothingType() + ", _clothingColor=" + this.getClothingColor() + ", _clothingSize=" + this.getClothingSize() + ", _clothingFabric=" + this.getClothingFabric() + ")";
    }
}
