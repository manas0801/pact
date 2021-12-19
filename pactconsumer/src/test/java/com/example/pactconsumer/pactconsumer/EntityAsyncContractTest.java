package com.example.pactconsumer.pactconsumer;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.model.v3.messaging.Message;
import au.com.dius.pact.model.v3.messaging.MessagePact;
import com.example.model.Entity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "entityAsyncProvider",providerType = ProviderType.ASYNCH)
public class EntityAsyncContractTest {



    @Pact(consumer = "entityAsyncConsumer",provider="entityAsyncProvider")
    public MessagePact createAsyncPact(MessagePactBuilder builder){

        PactDslJsonBody body = new PactDslJsonBody()
                .stringType("name", "iPhone")
                .stringType("timestamp", "timestamp");


        return builder
                .expectsToReceive("valid entity from provider")
                .withContent(body).toPact();

    }

    @PactTestFor(pactMethod = "createAsyncPact")
    @Test
    public void testValidDateFromProvider(List<Message> messages) throws JsonProcessingException {
        assertEquals(messages.isEmpty(),false);
        assertEquals(new ObjectMapper().readValue(new String(messages.get(0).contentsAsBytes()), Entity.class).getName(),"iPhone");

    }
}
