package com.example.demo.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ProductUpdateService {

    private final MongoTemplate mongoTemplate;

    public ProductUpdateService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void updateDescription(String id,
                                  String description) {

        Query query = new Query(
                Criteria.where("_id").is(id));

        Update update = new Update()
                .set("description", description);

        mongoTemplate.updateFirst(
                query,
                update,
                "products");
    }

    public void increaseStock(String id,
                              int quantity) {

        Query query = new Query(
                Criteria.where("_id").is(id));

        Update update = new Update()
                .inc("stock", quantity);

        mongoTemplate.updateFirst(
                query,
                update,
                "products");
    }
   
}