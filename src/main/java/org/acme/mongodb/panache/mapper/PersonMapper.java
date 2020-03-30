package org.acme.mongodb.panache.mapper;

import org.acme.mongodb.panache.model.AddressDto;
import org.acme.mongodb.panache.model.PersonDto;
import org.acme.mongodb.panache.model.StatusDto;
import org.acme.mongodb.panache.repository.Address;
import org.acme.mongodb.panache.repository.Person;
import org.acme.mongodb.panache.repository.Status;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "cdi")
public interface PersonMapper {


    @Mapping(target = "birth", source = "birthDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "status", source = "status", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PersonDto map(Person person);

    List<PersonDto> map(List<Person> person);

    @InheritInverseConfiguration(name = "map")
    Person map(PersonDto person);


    @ValueMappings({
            @ValueMapping(source = "LIVING", target = "LEBEND"),
            @ValueMapping(source = "DECEASED", target = "TOD")
    })
    StatusDto map(Status status);

    @InheritInverseConfiguration(name = "map")
    Status map(StatusDto statusDto);


    AddressDto map(Address address);

    Address map(AddressDto addressDto);

}
