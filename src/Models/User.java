package Models;

import java.time.LocalDate;

public class User {

    private int _userId;
    private String _firstname;
    private String _lastname;
    private LocalDate _birthdate;
    private Gender _gender;
    private String _email;
    private String _password;
    private Basket _basket;

    public User() {
    }
    public User(int userId, String firstName, String lastname, LocalDate birthDate, Gender gender, String email, String password, Basket basket){
        this.setUserId(userId);
        this.setFirstname(firstName);
        this.setLastname(lastname);
        this.setBirthdate(birthDate);
        this.setGender(gender);
        this.setEmail(email);
        this.setPassword(password);
        this.setBasket(basket);
    }


    public int getUserId() {
        return this._userId;
    }

    public String getFirstname() {
        return this._firstname;
    }

    public String getLastname() {
        return this._lastname;
    }

    public LocalDate getBirthdate() {
        return this._birthdate;
    }

    public Gender getGender() {
        return this._gender;
    }

    public String getEmail() {
        return this._email;
    }

    public String getPassword() {
        return this._password;
    }

    public Basket getBasket() {
        return this._basket;
    }

    public void setUserId(int _userId) {
        if(_userId >= 0){
            this._userId = _userId;
        }
    }

    public void setFirstname(String _firstname) {
        if(!_firstname.isBlank()){
            this._firstname = _firstname;
        }
    }

    public void setLastname(String lastname) {
        if(!lastname.isBlank()){
            this._lastname = lastname;
        }
    }

    public void setBirthdate(LocalDate _birthdate) {
        if(_birthdate.isBefore(LocalDate.now())){
            this._birthdate = _birthdate;
        }
    }

    public void setGender(Gender _gender) {
        this._gender = _gender;
    }

    public void setEmail(String _email) {
        if(_email.contains("@") && _email.contains(".")){
            this._email = _email;
        }
    }

    public void setPassword(String _password) {
        if(_password.length() > 7){
            this._password = _password;
        }
    }

    public void setBasket(Basket _basket) {
        this._basket = _basket;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getUserId() != other.getUserId()) return false;
        final Object this$_firstname = this.getFirstname();
        final Object other$_firstname = other.getFirstname();
        if (this$_firstname == null ? other$_firstname != null : !this$_firstname.equals(other$_firstname))
            return false;
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        if (this$lastname == null ? other$lastname != null : !this$lastname.equals(other$lastname)) return false;
        final Object this$birthdate = this.getBirthdate();
        final Object other$birthdate = other.getBirthdate();
        if (this$birthdate == null ? other$birthdate != null : !this$birthdate.equals(other$birthdate)) return false;
        final Object this$gender = this.getGender();
        final Object other$gender = other.getGender();
        if (this$gender == null ? other$gender != null : !this$gender.equals(other$gender)) return false;
        final Object this$_email = this.getEmail();
        final Object other$_email = other.getEmail();
        if (this$_email == null ? other$_email != null : !this$_email.equals(other$_email)) return false;
        final Object this$_password = this.getPassword();
        final Object other$_password = other.getPassword();
        if (this$_password == null ? other$_password != null : !this$_password.equals(other$_password)) return false;
        final Object this$_basket = this.getBasket();
        final Object other$_basket = other.getBasket();
        if (this$_basket == null ? other$_basket != null : !this$_basket.equals(other$_basket)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getUserId();
        final Object $_firstname = this.getFirstname();
        result = result * PRIME + ($_firstname == null ? 43 : $_firstname.hashCode());
        final Object $lastname = this.getLastname();
        result = result * PRIME + ($lastname == null ? 43 : $lastname.hashCode());
        final Object $birthdate = this.getBirthdate();
        result = result * PRIME + ($birthdate == null ? 43 : $birthdate.hashCode());
        final Object $gender = this.getGender();
        result = result * PRIME + ($gender == null ? 43 : $gender.hashCode());
        final Object $_email = this.getEmail();
        result = result * PRIME + ($_email == null ? 43 : $_email.hashCode());
        final Object $_password = this.getPassword();
        result = result * PRIME + ($_password == null ? 43 : $_password.hashCode());
        final Object $_basket = this.getBasket();
        result = result * PRIME + ($_basket == null ? 43 : $_basket.hashCode());
        return result;
    }

    public String toString() {
        return "User(_userId=" + this.getUserId() + ", _firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", birthdate=" + this.getBirthdate() + ", gender=" + this.getGender() + ", _email=" + this.getEmail() + ", _password=" + this.getPassword() + ", _basket=" + this.getBasket() + ")";
    }
}
