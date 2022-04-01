package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int _userId;
    private String _firstname;
    private String _lastname;
    private LocalDate _birthdate;
    private Gender _gender;
    private String _email;
    private String _password;
    private List<Address> _addresses = new ArrayList<>();
    private Basket _basket;

    public User() {
    }
    public User(int userId, String firstName, String lastname, LocalDate birthDate, Gender gender, String email, String password, List<Address> addresses, Basket basket){
        this.setUserId(userId);
        this.setFirstname(firstName);
        this.setLastname(lastname);
        this.setBirthdate(birthDate);
        this.setGender(gender);
        this.setEmail(email);
        this.setPassword(password);
        this.setAddresses(addresses);
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

    public List<Address> getAddresses(){
        return this._addresses;
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
        this._password = _password;
    }
    public void setAddresses(List<Address> addresses){
        if(addresses == null){
            return;
        }
        for(Address a: addresses){
            this._addresses.add(a);
        }
    }
    public void addAddress(Address addressToAdd){
        this._addresses.add(addressToAdd);
    }
    public void removeAddress(Address addressToRemove){
        if(_addresses.contains(addressToRemove)){
            this._addresses.remove(addressToRemove);
        }
    }

    public void setBasket(Basket _basket) {
        this._basket = _basket;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }



    public String toString() {
        return "User(_userId=" + this.getUserId() + ", _firstname=" + this.getFirstname() + ", _lastname=" + this.getLastname() + ", _birthdate=" + this.getBirthdate() + ", _gender=" + this.getGender() + ", _email=" + this.getEmail() + ", _addresses=" + this.getAddresses() + ", _basket=" + this.getBasket() + ")";
    }
}
