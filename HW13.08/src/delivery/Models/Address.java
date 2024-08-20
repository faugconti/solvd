package delivery.Models;

public class Address {
    private String country;
    private String zipCode;
    private String state;
    private String road;

    public Address(String country, String zipCode, String state, String road) {
        this.country = country;
        this.zipCode = zipCode;
        this.state = state;
        this.road = road;
    }

    public String getCountry() {
        return country;
    }

    public String getRoad() {
        return road;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Address))
            return false;
        Address other = (Address) obj;
        boolean roadEquals = (this.road == null && other.road == null)
                || (this.road != null && this.road.equals(other.road));
        boolean stateEquals = (this.state == null && other.state == null)
                || (this.state != null && this.state.equals(other.state));
        boolean zipCodeEquals = (this.zipCode == null && other.zipCode == null)
                || (this.zipCode != null && this.zipCode.equals(other.zipCode));
        boolean countryEquals = (this.country == null && other.country == null)
                || (this.country != null && this.country.equals(other.country));

        return roadEquals && stateEquals && zipCodeEquals && countryEquals;
    }

    @Override
    public int hashCode() {
        return (int) this.road.hashCode() * this.state.hashCode() * this.country.hashCode() * this.zipCode.hashCode();
    }

}
