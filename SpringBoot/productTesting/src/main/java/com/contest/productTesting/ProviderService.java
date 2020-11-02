package com.contest.productTesting;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ProviderService {
    RestTemplate restTemplate = new RestTemplate();

    public JSONObject getProductJSON(String url) {
        System.out.println("-----------------RESPONSE BODY START-----------------");
        JSONObject resJson = new JSONObject(restTemplate.getForObject(url, String.class));
        System.out.println("Name: " + resJson.get("name") + "\nPrice: " + resJson.get("price"));
        System.out.println("-----------------RESPONSE BODY END-----------------");
        return resJson;
    }

    public JSONObject getAllProductsJSON(String url) {
        System.out.println("-----------------RESPONSE BODY START-----------------");
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
        Product[] products = response.getBody();
        System.out.println(products);
        JSONObject resJson = new JSONObject(products);
        System.out.println("res JSON " + resJson);
        System.out.println("-----------------RESPONSE BODY END-----------------");
        return resJson;
    }

}
