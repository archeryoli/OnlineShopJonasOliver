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

    public Clothing(Article a) {
        super(a);
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

    public String toString() {
        return "Clothing(" + super.toString() + "_clothingType=" + this.getClothingType() + ", _clothingColor=" + this.getClothingColor() + ", _clothingSize=" + this.getClothingSize() + ", _clothingFabric=" + this.getClothingFabric() + ")";
    }
}
