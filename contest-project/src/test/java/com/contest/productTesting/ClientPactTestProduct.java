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
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Disabled
public class ClientPactTestProduct {

    @Autowired
    private ProviderService providerService;

    // This sets up a mock server that pretends to be our provider
    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("ProductProvider", this);

    private String name,price;

    // This defines the expected interaction for out test
    @Pact(consumer = "ProductConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        this.name = "Apple iPhone X";
        this.price = "70000";

        return builder
                .given("")
                .uponReceiving("Request for Product JSON")
                .path("/product")
                .query("pid=101")
                .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(
                            new PactDslJsonBody()
                            .stringValue("name", name)
                            .stringValue("price", price)
                    ).toPact();
    }

    @Test
    @PactVerification
    public void pactWithProvider() throws Exception {
        System.out.println("Server started at " + mockProvider.getUrl());
        JSONObject res = providerService.getProductJSON(mockProvider.getUrl()+"/product?pid=101");
        assertThat(res.get("name")).isEqualTo(name);
        assertThat(res.get("price")).isEqualTo(price);
    }

}
