package com.example.demo.tasklist;

import com.example.demo.persistence.PizzaTaskRepository;
import com.example.demo.persistence.model.PizzaTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pizza-task-list")
public class PizzaTaskListController {

    @Autowired
    private PizzaTaskRepository pizzaTaskRepository;

    @GetMapping
    public List<PizzaTask> getCustomTaskList() {
        return pizzaTaskRepository.findAll();
    }
}
