package com.example.pactconsumer.pactconsumer;


import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.model.Entity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "entityProvider",port="8088")
public class EntityContractTest {

@Pact(consumer = "entityConsumer",provider="entityProvider")
public RequestResponsePact createPact(PactDslWithProvider builder){

    PactDslJsonBody body = new PactDslJsonBody()
            .stringType("name", "iPhone")
            .stringType("timestamp", "timestamp");


    Map<String,String> headers = new HashMap();
    headers.put("Content-Type","application/json");

    return builder
            .given("Get entity details")
            .uponReceiving("Get etity details for entity Name")
            .path("/entity/iPhone")
            .method(HttpMethod.GET.name())
            .willRespondWith()
            .status(HttpStatus.OK.value())
            .headers(headers)
            .body(body)
            .toPact();
}


    @Test
    void test(MockServer mockServer) throws Exception {
     Entity entity=  new RestTemplate().getForObject(mockServer.getUrl()+"/entity/iPhone", Entity.class) ;

     assertEquals(entity.getName(), "iPhone");
    }


}
