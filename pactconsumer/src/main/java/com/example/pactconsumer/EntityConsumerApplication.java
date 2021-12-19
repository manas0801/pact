package com.example.pactconsumer;

import com.example.model.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EntityConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(EntityConsumerApplication.class, args);
  }

  @GetMapping("/test")
  public String ping() {

    return "Hello World";
  }

  @KafkaListener(topics = "entity2", groupId = "grp-1")
  public void listenGroupFoo(Entity entity) {

    System.out.println("Received Message in group foo: " + entity.getName());
  }
}
