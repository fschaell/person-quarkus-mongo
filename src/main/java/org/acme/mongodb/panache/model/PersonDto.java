package org.acme.mongodb.panache.model;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class PersonDto {

    public ObjectId id;

    private String name;

    public LocalDate birth;

    private AddressDto address;

    public StatusDto status;

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public StatusDto getStatus() {
        return status;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
