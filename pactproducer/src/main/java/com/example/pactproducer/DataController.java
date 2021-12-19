package com.example.pactproducer;



import com.example.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping
@RestController
public class DataController {



    @Autowired
    EntityRepository entityRepository;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @GetMapping(path="/entity",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Entity>> getEntities(){

        return new ResponseEntity<>(entityRepository.getEntities(), HttpStatus.OK);
    }
    @GetMapping(path="/entity/{entityId}",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Entity> getEntity(@PathVariable(value ="entityId") String entityId){

        return new ResponseEntity<>(entityRepository.getEntity(entityId),HttpStatus.OK);
    }

    @PostMapping(path="/entity",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postEntity(@RequestBody Entity entity){

        entityRepository.addEntity(entity);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(path="/entity/publish",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postEntityForPublish(@RequestBody Entity entity){

        kafkaTemplate.send("entity2",entity);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
