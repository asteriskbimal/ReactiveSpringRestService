package com.reactive.couchbase.controller;

import com.reactive.couchbase.model.Entity;
import com.reactive.couchbase.service.EntityHystrixCommand;
import com.reactive.couchbase.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class EntityController {

    @Autowired
    GenericService<Entity> genericService;

    @Autowired
    EntityHystrixCommand entityHystrixCommand;

    @GetMapping("/entity/{id}/{name}")
    private Mono<Entity> getEmployeeById(@PathVariable String id, @PathVariable String name) {
        Entity e=new Entity();
        e.setId(id);
        e.setName(name);
        return genericService.get(e);
    }

    @GetMapping("/entity")
    private List<Entity> getAllEmployees() {
         Flux<Entity> flux=genericService.getAll(new Entity());
        return flux.collectList().block();
    }

    @GetMapping("/entity/string/{id}/{name}")
    private String getEmployesStringById(@PathVariable String id, @PathVariable String name) {
        Entity e=new Entity();
        e.setId(id);
        e.setName(name);
        entityHystrixCommand.setEntity(e);
        return entityHystrixCommand.execute();
    }

    public GenericService<Entity> getGenericService() {
        return genericService;
    }

    public void setGenericService(GenericService<Entity> genericService) {
        this.genericService = genericService;
    }

    public EntityHystrixCommand getEntityHystrixCommand() {
        return entityHystrixCommand;
    }

    public void setEntityHystrixCommand(EntityHystrixCommand entityHystrixCommand) {
        this.entityHystrixCommand = entityHystrixCommand;
    }
}
