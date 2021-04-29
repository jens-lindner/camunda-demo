package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SelectPizzaToppingService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String weekday = (String) delegateExecution.getVariable(ProcessVariables.weekday);

        String selectedTopping = weekday != null && weekday.equalsIgnoreCase("saturday") ? "salami" : "hawai";
        log.info("Selected Topping: {}", selectedTopping);

        delegateExecution.setVariable(ProcessVariables.selectedPizzaTopping, selectedTopping);
    }
}
