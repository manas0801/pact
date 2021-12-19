package com.example.pactproducer;



import com.example.model.Entity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EntityRepository {

    public Map<String, Entity> entityMap= new HashMap<>();

    public List<Entity> getEntities(){

        return entityMap.values().parallelStream().collect(Collectors.toList());
    }
    public Entity getEntity(String entityName){

        return new Entity("iPhone");

       // return entityMap.get(entityName);
    }

    public  void addEntity(Entity entity){

        entityMap.put(entity.getName(),entity);
    }
}
