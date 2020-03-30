package org.acme.mongodb.panache;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.mongodb.panache.mapper.PersonMapper;
import org.acme.mongodb.panache.model.AddressDto;
import org.acme.mongodb.panache.model.PersonDto;
import org.acme.mongodb.panache.model.StatusDto;
import org.acme.mongodb.panache.repository.Person;
import org.junit.jupiter.api.Test;


import javax.inject.Inject;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestPersonMapper {

    @Inject
    PersonMapper personMapper;


    @Test
    public void shouldAutomapAndHandleSourceAndTargetPropertyNesting() {
        PersonDto source = createPersonDto();
        // -- action

        // -- action
        Person target = personMapper.map( source );


        // -- result
        assertEquals( target.getName(),source.getName());

    }

    private PersonDto createPersonDto() {

        PersonDto personDto = new PersonDto();
        personDto.setName("Horst Schlemmer");
        personDto.setBirth(LocalDate.now());
        personDto.status = StatusDto.LEBEND;
        personDto.setAddress(new AddressDto() {
            @Override
            public void setCity(String city) {
                super.setCity("Erfurt");
            }

            @Override
            public void setStreet(String street) {
                super.setStreet("Teststr");
            }

            @Override
            public void setZipCode(String zipCode) {
                super.setZipCode("99088");
            }
        });
        return personDto;
    }

}