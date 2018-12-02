package com.reactive.couchbase.controller;

import com.reactive.couchbase.model.Entity;
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
}
