package org.example.domain;

import org.example.domain.embeddable.Address;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @GeneratedValue
    @Column(name = "ADDRESS_ID")
    Long id;

    @Embedded
    Address address;

    protected AddressEntity() {
    }

    public AddressEntity(String city, String street, String zipcode){
        this.address = new Address(city,street, zipcode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
