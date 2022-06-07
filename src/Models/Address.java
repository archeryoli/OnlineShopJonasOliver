package Models;

public class Address {
    private int _addressId;
    private String _state;
    private String _postalcode;
    private String _city;
    private String _street;
    private String _streetNr;



    public int getAddressId() {
        return this._addressId;
    }

    public String getState() {
        return this._state;
    }

    public String getPostalcode() {
        return this._postalcode;
    }

    public String getCity() {
        return this._city;
    }

    public String getStreet() {
        return this._street;
    }

    public String getStreetNr() {
        return this._streetNr;
    }

    public void setAddressId(int _addressId) {
        if (_addressId >= 0) {
            this._addressId = _addressId;
        }
    }

    public void setState(String _state) {
        if (!(_state.isEmpty())) {
            this._state = _state;
        }
    }

    public void setPostalcode(String _postalcode) {
        if(!(_postalcode.isEmpty())) {
            this._postalcode = _postalcode;
        }
    }

    public void setCity(String _city) {
        if(!(_city.isEmpty())) {
            this._city = _city;
        }
    }

    public void setStreet(String _street) {
        if(!(_street.isEmpty())) {
            this._street = _street;
        }
    }

    public void setStreetNr(String _streetNr) {
        if(!(_streetNr.isEmpty())) {
            this._streetNr = _streetNr;
        }
    }

    public Address(){
        this(0, "", "", "", "", "");
    };

    public Address(int _addressId, String _state, String _postalcode, String _city, String _street, String _streetNr){
        this.setAddressId(_addressId);
        this.setState(_state);
        this.setPostalcode(_postalcode);
        this.setCity(_city);
        this.setStreet(_street);
        this.setStreetNr(_streetNr);
    }

    public String toString() {
        return "\n(_addressId=" + this.getAddressId() + ", _state=" + this.getState() + ", _postalcode=" + this.getPostalcode() + ", _city=" + this.getCity() + ", _street=" + this.getStreet() + ", _streetNr=" + this.getStreetNr() + ")";
    }
}
