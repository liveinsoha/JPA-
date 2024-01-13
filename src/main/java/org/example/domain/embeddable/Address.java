package org.example.domain.embeddable;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
    String city;
    String street;

    @Column(name = "ZEAPCODE")
    String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Address address = (Address) object;
        return Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getZipcode(), address.getZipcode());
    }

    /**
     * 필드에 직접 접근할 수도 있지만 프록시같이 복잡한 경우 getter를 사용하는 게 나을 수 있다.
     *
     * @return
     */

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getCity(), getZipcode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
