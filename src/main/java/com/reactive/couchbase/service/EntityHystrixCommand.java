package com.reactive.couchbase.service;

import com.netflix.hystrix.*;
import com.reactive.couchbase.model.Entity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.lang.String;

@Service
@Scope(value= WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EntityHystrixCommand extends HystrixCommand<String> {

    private final EntityService entityService;

    private Entity entity;

    public EntityHystrixCommand(final EntityService entityService) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("entity"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("entityGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(5000)
                                .withMetricsHealthSnapshotIntervalInMilliseconds(1000)
                                .withMetricsRollingStatisticalWindowInMilliseconds(20000)
                                .withCircuitBreakerSleepWindowInMilliseconds(10000)
                )
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GeoIp"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(4)
                                .withMaxQueueSize(10)
                )

        );

        this.entityService = entityService;

    }

    @Override
    protected String run() throws Exception{
        if(true){
            thorw new Exception();
        }
        return entityService.businessLogicSpecificToEntity(getEntity());
    }
    // deploy the service in different nodes created in different zones within same Google cloud kubernetes cluster and check the date zone 
    @Override
    protected String getFallback(){
        return "Entity Service failed to respond at the time :" + new java.util.Date();
    }

    @Override
    protected boolean isFallbackUserDefined(){
        return true;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
