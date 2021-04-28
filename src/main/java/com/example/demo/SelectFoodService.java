package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SelectFoodService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String weekday = (String) delegateExecution.getVariable(ProcessVariables.weekday);
        log.info("weekday: {}", weekday);

        String selectedFood = weekday != null && weekday.equalsIgnoreCase("monday") ? "salad" : "pizza";
        log.info("Selected Food: {}", selectedFood);

        delegateExecution.setVariable(ProcessVariables.selectedFood, selectedFood);
    }
}
