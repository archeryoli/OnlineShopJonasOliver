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

    public String get_streetNr() {
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Address)) return false;
        final Address other = (Address) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAddressId() != other.getAddressId()) return false;
        final Object this$_state = this.getState();
        final Object other$_state = other.getState();
        if (this$_state == null ? other$_state != null : !this$_state.equals(other$_state)) return false;
        final Object this$_postalcode = this.getPostalcode();
        final Object other$_postalcode = other.getPostalcode();
        if (this$_postalcode == null ? other$_postalcode != null : !this$_postalcode.equals(other$_postalcode))
            return false;
        final Object this$_city = this.getCity();
        final Object other$_city = other.getCity();
        if (this$_city == null ? other$_city != null : !this$_city.equals(other$_city)) return false;
        final Object this$_street = this.getStreet();
        final Object other$_street = other.getStreet();
        if (this$_street == null ? other$_street != null : !this$_street.equals(other$_street)) return false;
        final Object this$_streetNr = this.get_streetNr();
        final Object other$_streetNr = other.get_streetNr();
        if (this$_streetNr == null ? other$_streetNr != null : !this$_streetNr.equals(other$_streetNr)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Address;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAddressId();
        final Object $_state = this.getState();
        result = result * PRIME + ($_state == null ? 43 : $_state.hashCode());
        final Object $_postalcode = this.getPostalcode();
        result = result * PRIME + ($_postalcode == null ? 43 : $_postalcode.hashCode());
        final Object $_city = this.getCity();
        result = result * PRIME + ($_city == null ? 43 : $_city.hashCode());
        final Object $_street = this.getStreet();
        result = result * PRIME + ($_street == null ? 43 : $_street.hashCode());
        final Object $_streetNr = this.get_streetNr();
        result = result * PRIME + ($_streetNr == null ? 43 : $_streetNr.hashCode());
        return result;
    }

    public String toString() {
        return "Address(_addressId=" + this.getAddressId() + ", _state=" + this.getState() + ", _postalcode=" + this.getPostalcode() + ", _city=" + this.getCity() + ", _street=" + this.getStreet() + ", _streetNr=" + this.get_streetNr() + ")";
    }
}
