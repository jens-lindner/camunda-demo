package com.example.demo.service;

import com.example.demo.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SelectFoodService implements JavaDelegate {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SelectFoodService.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String weekday = (String) delegateExecution.getVariable(ProcessVariables.weekday);
        log.info("weekday: {}", weekday);

        String selectedFood = weekday != null && weekday.equalsIgnoreCase("monday") ? "salad" : "pizza";
        log.info("Selected Food: {}", selectedFood);

        delegateExecution.setVariable(ProcessVariables.selectedFood, selectedFood);
    }
}
