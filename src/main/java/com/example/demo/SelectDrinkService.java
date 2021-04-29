package com.example.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SelectDrinkService implements JavaDelegate {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SelectDrinkService.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String selectedFood = (String) delegateExecution.getVariable(ProcessVariables.selectedFood);

        String selectedDrink = selectedFood.equalsIgnoreCase("pizza") ? "cola" : "water";
        log.info("Selected Drink: {}", selectedDrink);

        delegateExecution.setVariable(ProcessVariables.selectedDrink, selectedDrink);
    }
}
