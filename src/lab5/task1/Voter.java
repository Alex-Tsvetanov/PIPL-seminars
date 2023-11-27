package lab5.task1;

public class Voter {
    private String name;
    private String city;
    private String street;
    private String address;

    public Voter(String name, String city, String street, String address) {
        this.setName(name);
        this.setCity(city);
        this.setStreet(street);
        this.setAddress(address);
    }

    public String getAddress() {
        return address;
    }

    private Voter setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getStreet() {
        return street;
    }

    private Voter setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getCity() {
        return city;
    }

    private Voter setCity(String city) {
        this.city = city;
        return this;
    }

    public String getName() {
        return name;
    }

    private Voter setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Voter {" + getName() + ", " + getCity() + ", " + getStreet() + ", " + getAddress() + "}";
    }
}
