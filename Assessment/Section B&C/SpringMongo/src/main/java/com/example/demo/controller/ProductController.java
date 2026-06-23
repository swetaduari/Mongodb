package com.example.demo.controller;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;

@Controller
public class ProductController {

    private final ProductRepo repository;
    private final MongoTemplate mongoTemplate;

    public ProductController(ProductRepo repository,
                             MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Product> products = repository.findAll();
        model.addAttribute("products", products);

        return "products";
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product) {

        repository.save(product);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {

        repository.deleteById(id);

        return "redirect:/";
    }

    @PostMapping("/updateDescription")
    public String updateDescription(
            @RequestParam String id,
            @RequestParam String description) {

        Query query = new Query(
                Criteria.where("_id").is(id));

        Update update = new Update()
                .set("description", description);

        mongoTemplate.updateFirst(
                query,
                update,
                Product.class);

        return "redirect:/";
    }

    @PostMapping("/updateStock")
    public String updateStock(
            @RequestParam String id,
            @RequestParam int quantity) {

        Query query = new Query(
                Criteria.where("_id").is(id));

        Update update = new Update()
                .inc("stock", quantity);

        mongoTemplate.updateFirst(
                query,
                update,
                Product.class);

        return "redirect:/";
    }
}