package com.example.pactconsumer.service;



import com.example.model.Entity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EntityService {

    public List<Entity> getEntities(){

     return new RestTemplate().exchange("http://localhost:8081/entity/", HttpMethod.GET,null,new ParameterizedTypeReference<List<Entity>>() {}).getBody();


    }

    public Entity getEntity(){

        return new RestTemplate().getForObject("http://localhost:8081/entity/iPhone",Entity.class);


    }
}
