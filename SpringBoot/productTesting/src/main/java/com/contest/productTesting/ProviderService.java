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

    public JSONArray getProductJSONArray(String url) {
        System.out.println("-----------------RESPONSE BODY START-----------------");
        JSONArray resJson = new JSONArray(restTemplate.getForObject(url, String.class));
        System.out.println("Response Body"  + resJson);
        System.out.println("-----------------RESPONSE BODY END-----------------");
        return resJson;
    }

    public JSONObject getProductJSON(String url) {
        System.out.println("-----------------RESPONSE BODY START-----------------");
        JSONObject resJson = new JSONObject(restTemplate.getForObject(url, String.class));
        System.out.println("Name: " + resJson.get("name") + "\nPrice: " + resJson.get("price"));
        System.out.println("-----------------RESPONSE BODY END-----------------");
        return resJson;
    }


}
