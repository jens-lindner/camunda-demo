package com.example.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ValidationService implements JavaDelegate {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ValidationService.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String selectedFood = (String) delegateExecution.getVariable(ProcessVariables.selectedFood);
        String selectedDrink = (String) delegateExecution.getVariable(ProcessVariables.selectedDrink);

        if (selectedFood != null && selectedDrink != null) {
            log.info("Validation successful!");
            delegateExecution.setVariable(ProcessVariables.foodAndOrderSelected, true);
        }
    }
}
