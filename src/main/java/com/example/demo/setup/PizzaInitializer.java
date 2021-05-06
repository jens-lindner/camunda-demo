package com.example.demo.setup;

import com.example.demo.persistence.model.Pizza;
import com.example.demo.persistence.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PizzaInitializer {

    @Autowired
    private PizzaRepository pizzaRepository;

    @PostConstruct
    public void initPizza() {
        System.out.println("--- initialize Pizza Table ---");

        Pizza margherita = new Pizza();
        margherita.setPrice(8.99);
        margherita.setName("margherita");
        pizzaRepository.save(margherita);

        Pizza speziale = new Pizza();
        speziale.setPrice(11.99);
        speziale.setName("speziale");
        pizzaRepository.save(speziale);

        System.out.println("--- initialized Pizza Table  with " + pizzaRepository.count() + " pizzas ---");
    }
}
