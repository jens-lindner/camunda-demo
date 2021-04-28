package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidationService implements JavaDelegate {

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
