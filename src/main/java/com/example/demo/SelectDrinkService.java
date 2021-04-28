package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SelectDrinkService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String selectedFood = (String) delegateExecution.getVariable(ProcessVariables.selectedFood);

        String selectedDrink = selectedFood.equalsIgnoreCase("pizza") ? "cola" : "water";
        log.info("Selected Drink: {}", selectedDrink);

        delegateExecution.setVariable(ProcessVariables.selectedDrink, selectedDrink);
    }
}
