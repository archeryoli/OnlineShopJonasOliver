package Models;

public class Electronics extends Article {
    private String _electronicsModel;
    private double _electronicsWattage;
    private String _electronicsDimensions;

    public Electronics() {
        this(0, "", 0.0, "", "", 0.0, 0, "", 0.0, "");
    }
    public Electronics(int productId, String productName, double productPrice, String productBrand, String productDescription, double productWeight, int productStockCount,
                       String electronicsModel, double electronicsWattage, String electronicsDimensions){
        super(productId, productName, productPrice, productBrand, productDescription, productWeight, productStockCount);
        this.setElectronicsModel(electronicsModel);
        this.setElectronicsWattage(electronicsWattage);
        this.setElectronicsDimensions(electronicsDimensions);
    }

    public Electronics(Article a) {
        super(a);
    }

    public String getElectronicsModel() {
        return this._electronicsModel;
    }

    public double getElectronicsWattage() {
        return this._electronicsWattage;
    }

    public String getElectronicsDimensions() {
        return this._electronicsDimensions;
    }

    public void setElectronicsModel(String _electronicsModel) {
        if(!(_electronicsModel.isEmpty())){
            this._electronicsModel = _electronicsModel;
        }
    }

    public void setElectronicsWattage(double _electronicsWattage) {
        if(_electronicsWattage > 0){
            this._electronicsWattage = _electronicsWattage;
        }
    }

    public void setElectronicsDimensions(String _electronicsDimensions) {
        if(!(_electronicsDimensions.isEmpty())){
            this._electronicsDimensions = _electronicsDimensions;
        }
    }

    public String toString() {
        return "Electronics(" + super.toString() + "_electronicsModel=" + this.getElectronicsModel() + ", _electronicsWattage=" + this.getElectronicsWattage() + ", _electronicsDimensions=" + this.getElectronicsDimensions() + ")";
    }



}
