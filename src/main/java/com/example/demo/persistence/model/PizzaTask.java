package com.example.demo.persistence.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PizzaTask {

    @Id
    private String id;
    private String taskName;

    private String pizzaName;
    private String businessData1;
    private String businessData2;
}
