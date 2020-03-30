package org.acme.mongodb.panache.repository;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@MongoEntity(collection="Personen")
public class Person extends PanacheMongoEntity {

    //@BsonId
    //public ObjectId id;

    private String name;

    // will be persisted as a 'birth' field in MongoDB
    @BsonProperty("geburtstag")
    public LocalDate birthDate;

    public Status status;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // return name as uppercase in the model
    public String getName(){
        return name;
    }

    // store all names in lowercase in the DB
    public void setName(String name){
        this.name = name;
    }

    // entity methods
    public static Person findByName(String name){
        Person p = find("name", name).firstResult();
        return p;
    }

    public static List<Person> findAlive(){
        return list("status", Status.LIVING);
    }

    public static void deleteLoics(){
        delete("name", "Loïc");
    }
}