package com.reactive.couchbase.service;

import com.reactive.couchbase.model.Entity;
import com.reactive.couchbase.repository.GenericDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService extends GenericService<Entity> {

    @Autowired
    public EntityService(GenericDaoImpl<Entity> genericDaoImpl) {
        this.genericDaoImpl = genericDaoImpl;
    }

    public String businessLogicSpecificToEntity(Entity entity){
        entity=genericDaoImpl.get(genericDaoImpl.createKey(entity),Entity.class).block();
        return "businessLogicSpecificToEntity:"+entity.toString();
    }

}
