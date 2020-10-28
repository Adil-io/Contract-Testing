package com.contest.productTesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        List<Product> products = new LinkedList<>();
        for(Product product: productRepository.findAll()) {
            products.add(product);
        }
        return products;
    }

    @GetMapping("/product")
    public Product getProduct(@RequestParam(value = "pid", required = true) int pid) {
        for(Product product: productRepository.findAll()) {
            if(product.getPid() == pid) {
                return product;
            }
        }
        return new Product("",-1,"null","null");
    }

    @PostMapping("/create-product")
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

}
