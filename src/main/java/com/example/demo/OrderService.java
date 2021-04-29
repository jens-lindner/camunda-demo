package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String selectedFood = (String) delegateExecution.getVariable(ProcessVariables.selectedFood);
        String selectedDrink = (String) delegateExecution.getVariable(ProcessVariables.selectedDrink);

        log.info("Hello, I'd like to order {} and {}", selectedFood, selectedDrink);
        if( selectedFood.equalsIgnoreCase("pizza")) {
            String selectedPizzaTopping = (String) delegateExecution.getVariable(ProcessVariables.selectedPizzaTopping);
            log.info("The pizza with {}", selectedPizzaTopping);
        }
    }
}
