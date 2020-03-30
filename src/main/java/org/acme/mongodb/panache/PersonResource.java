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
    public Response get(@PathParam("id") String id){
        Person person = Person.findById(new ObjectId(id));
        PersonDto personDto = personMapper.map(person);
        if (person == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.FOUND).entity(personDto).build();
    }

    @POST
    public Response create(PersonDto personDto){
        Person p = personMapper.map(personDto);
        p.persist();
        PersonDto personDtoResult = personMapper.map(p);
        return Response.status(Response.Status.CREATED).entity(personDtoResult).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, PersonDto personDto)
    {
        Person p = personMapper.map(personDto);
        p.id = new ObjectId(id);
        p.update();
        PersonDto personResult = personMapper.map(p);
        return Response.status(Response.Status.ACCEPTED).entity(personResult).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id){
        Person person = Person.findById(new ObjectId(id));

        person.delete();

        PersonDto personDelete = personMapper.map(person);
        return Response.status(Response.Status.ACCEPTED).entity(personDelete).build();
    }

    @GET
    @Path("/search/{name}")
    public Response search(@PathParam("name") String name){
        Person p = Person.findByName(name);
        PersonDto person = personMapper.map(p);
        if (person == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.FOUND).entity(person).build();

    }

    @GET
    @Path("/count")
    public Long count(){
        return Person.count();
    }
}
