package delivery.Models.Person;

import delivery.Models.Address;

public class Person {
    private String name;
    private String email;
    private Address address;

    public Person(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String message = "name: " + this.name + " | email: " + this.email + " | Address: " + this.address.getRoad()
                + "(" + this.address.getState() + "," + this.address.getCountry() + ")";
        return message;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (name == null ? 0 : name.hashCode());
        hash = 31 * hash + (email == null ? 0 : email.hashCode());
        hash = 31 * hash + (address == null ? 0 : address.hashCode()); //address already have hashCode method
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        boolean nameEquals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        boolean emailEquals = (this.email == null && other.email == null)
                || (this.email != null && this.email.equals(other.email));
        boolean addressEquals = this.address.equals(other.address); // address already have equals method

        return nameEquals && emailEquals && addressEquals;
    }

}
