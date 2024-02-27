public class Address {
    private String houseNo;
    private String zipCode;
    private String state;
    private String country;

    public Address(String houseNo, String zipCode, String state, String country) {
        this.houseNo = houseNo;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNo='" + houseNo + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

