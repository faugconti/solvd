package delivery.Models.Person;

public class Person {
    private String name;
    private String email;
    private String address;

    public Person(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

}
