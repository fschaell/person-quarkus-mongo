package org.acme.mongodb.panache;

import org.acme.mongodb.panache.mapper.PersonMapper;
import org.acme.mongodb.panache.model.PersonDto;
import org.acme.mongodb.panache.repository.Person;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonMapper personMapper;

    @GET
    public List<PersonDto> list(){
        return personMapper.map(Person.listAll());
    }

    @GET
    @Path("/{id}")
    public PersonDto get(@PathParam("id") String id){
        Person person = Person.findById(new ObjectId(id));

        return personMapper.map(person);
    }

    @POST
    public Response create(PersonDto personDto){
        Person p = personMapper.map(personDto);
                p.persist();
        return Response.status(Response.Status.CREATED).entity(p).build();
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, PersonDto personDto)
    {

        personMapper.map(personDto).update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id){
        Person person = Person.findById(new ObjectId(id));
        person.delete();
    }

    @GET
    @Path("/search/{name}")
    public PersonDto search(@PathParam("name") String name){
        Person p = Person.findByName(name);
        PersonDto person = personMapper.map(p);
        return person;
    }

    @GET
    @Path("/count")
    public Long count(){
        return Person.count();
    }
}
