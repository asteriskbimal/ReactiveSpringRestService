package com.reactive.couchbase.service;

import com.reactive.couchbase.repository.GenericDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GenericService<T> {

    protected  GenericDaoImpl<T> genericDaoImpl;

    public Flux<T> getAll(T entity){
        return genericDaoImpl.getAll((entity));
    }

    public Mono<T> get(T entity){
        return genericDaoImpl.get(genericDaoImpl.createKey(entity),(Class<T>)entity.getClass());
    }
}
