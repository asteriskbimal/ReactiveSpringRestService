package com.reactive.couchbase.model;

import lombok.Data;

@Data
public class Entity {
    @BucketKey
    private String id;
    @BucketKey
    private String name;
    private String address;
    private String company;
    private String type;
}
