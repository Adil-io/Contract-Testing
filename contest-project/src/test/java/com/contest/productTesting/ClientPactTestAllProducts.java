package com.contest.productTesting;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.MessagePact;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.attribute.standard.Media;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientPactTestAllProducts {

    @Autowired
    private ProviderService providerService;

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("AllProductsProvider", this);

    @Pact(consumer = "AllProductsConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        return builder
                .given("")
                .uponReceiving("Request for All Products JSON")
                .path("/allProducts")
                .willRespondWith()
                .status(200)
                .body(
                        "[{\"id\":\"5f9923264ec3a446fad5ebae\",\"pid\":101,\"name\":\"Apple iPhone X\",\"price\":\"70000\",\"imgUrl\":\"http\"}]"
               ).toPact();
    }

    @Test
    @PactVerification
    public void hello() {
        TestRestTemplate obj = new TestRestTemplate();
        JSONArray responseArray= providerService.getProductJSONArray(mockProvider.getUrl() + "/allProducts");
        JSONObject res= (JSONObject) responseArray.get(0);
//        assertThat(res.get("pid")).isEqualTo(101);
//        assertThat(res.get("name")).isEqualTo("Apple iPhone X");
////        assertThat(res.get("price")).isEqualTo("70000");
    }
}
