package com.example.demo.listener;

import com.example.demo.persistence.PizzaTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CompletePizzaTaskListener implements TaskListener {

    @Autowired
    private PizzaTaskRepository pizzaTaskRepository;

    @Override
    public void notify(DelegateTask delegateTask) {
        pizzaTaskRepository.deleteById(delegateTask.getId());
        log.info("Completed PizzaTask with id: {}", delegateTask.getId());
    }
}
