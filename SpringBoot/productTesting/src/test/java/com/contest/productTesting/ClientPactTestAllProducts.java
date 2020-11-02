package com.contest.productTesting;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientPactTestAllProducts {

    private int pid;
    private String name, price;

    @Autowired
    private ProviderService providerService;

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("AllProductsProvider", this);

    @Pact(consumer = "AllProductsConsumer", provider = "AllProductsProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        this.pid = 101;
        this.name = "Apple iPhone X";
        this.price = "70000";

        return builder
                .given("")
                .uponReceiving("Request for All Products JSON")
                .path("/allProducts")
                .method("/GET")
                .willRespondWith()
                    .status(200)
                    .body(
                            new PactDslJsonBody()
                            .minArrayLike("", 1)
                            .integerType("pid", pid)
                            .stringValue("name", name)
                            .stringValue("price", price)
                    ).toPact();
    }

    @Test
    @PactVerification
    public void pactWithProvider() throws Exception {
        System.out.println("Server started at " + mockProvider.getUrl());
        JSONObject res = providerService.getAllProductsJSON(mockProvider.getUrl()+"/allProducts");
//        assertThat(res.get("pid")).isEqualTo(pid);
//        assertThat(res.get("name")).isEqualTo(name);
//        assertThat(res.get("price")).isEqualTo(price);
    }

}
