package com.example.demo.service;

import com.example.demo.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SelectPizzaToppingService implements JavaDelegate {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SelectPizzaToppingService.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String weekday = (String) delegateExecution.getVariable(ProcessVariables.weekday);

        String selectedTopping = weekday != null && weekday.equalsIgnoreCase("saturday") ? "salami" : "hawai";
        log.info("Selected Topping: {}", selectedTopping);

        delegateExecution.setVariable(ProcessVariables.selectedPizzaTopping, selectedTopping);
    }
}
