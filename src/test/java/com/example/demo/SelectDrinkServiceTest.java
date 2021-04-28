package com.example.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.pvm.runtime.ExecutionImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectDrinkServiceTest {

    private final SelectDrinkService service = new SelectDrinkService();

    @Test
    void testExecuteWithPizza() throws Exception {
        DelegateExecution delegateExecution = new ExecutionImpl();
        delegateExecution.setVariable(ProcessVariables.selectedFood, "pizza");

        service.execute(delegateExecution);

        assertThat(delegateExecution.getVariables()).containsEntry(ProcessVariables.selectedDrink, "cola");
    }
}
