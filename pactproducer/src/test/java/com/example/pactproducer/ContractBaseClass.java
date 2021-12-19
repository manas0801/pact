package com.example.pactproducer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractBaseClass {

    @Autowired
    DataController dataController;

    @BeforeEach
    private void setUp(){
        StandaloneMockMvcBuilder mockMvcBuilder = MockMvcBuilders.standaloneSetup(dataController);
        RestAssuredMockMvc.standaloneSetup(mockMvcBuilder);
    }

}
