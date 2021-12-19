package com.example.pactproducer.pactprovider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;

import com.example.model.Entity;
import com.example.pactproducer.EntityRepository;
import com.example.pactproducer.PactproducerApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@Provider("entityProvider")
@PactBroker(host="localhost",port="9292")
@SpringBootTest(classes = PactproducerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = "server.port=8080")
public class EntityProviderContractTest {


    @BeforeEach
    void setupTestTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8080, ""));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }


    @State("Get entity details")
    public void toDefaultState() {

        EntityRepository mockBean= Mockito.mock(EntityRepository.class);
        Mockito.when(mockBean.getEntity("iPhone")).thenReturn(new Entity("iPhone"));
    }


}
