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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Electronics)) return false;
        final Electronics other = (Electronics) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$_electronicsModel = this.getElectronicsModel();
        final Object other$_electronicsModel = other.getElectronicsModel();
        if (this$_electronicsModel == null ? other$_electronicsModel != null : !this$_electronicsModel.equals(other$_electronicsModel))
            return false;
        if (Double.compare(this.getElectronicsWattage(), other.getElectronicsWattage()) != 0) return false;
        final Object this$_electronicsDimensions = this.getElectronicsDimensions();
        final Object other$_electronicsDimensions = other.getElectronicsDimensions();
        if (this$_electronicsDimensions == null ? other$_electronicsDimensions != null : !this$_electronicsDimensions.equals(other$_electronicsDimensions))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Electronics;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $_electronicsModel = this.getElectronicsModel();
        result = result * PRIME + ($_electronicsModel == null ? 43 : $_electronicsModel.hashCode());
        final long $_electronicsWattage = Double.doubleToLongBits(this.getElectronicsWattage());
        result = result * PRIME + (int) ($_electronicsWattage >>> 32 ^ $_electronicsWattage);
        final Object $_electronicsDimensions = this.getElectronicsDimensions();
        result = result * PRIME + ($_electronicsDimensions == null ? 43 : $_electronicsDimensions.hashCode());
        return result;
    }

    public String toString() {
        return "Electronics(" + super.toString() + "_electronicsModel=" + this.getElectronicsModel() + ", _electronicsWattage=" + this.getElectronicsWattage() + ", _electronicsDimensions=" + this.getElectronicsDimensions() + ")";
    }
}
