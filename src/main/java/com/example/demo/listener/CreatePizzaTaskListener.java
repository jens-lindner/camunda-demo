package com.example.demo.listener;

import com.example.demo.persistence.PizzaRepository;
import com.example.demo.persistence.PizzaTaskRepository;
import com.example.demo.persistence.model.PizzaTask;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreatePizzaTaskListener implements TaskListener {

    @Autowired
    private PizzaTaskRepository pizzaTaskRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public void notify(DelegateTask delegateTask) {
        PizzaTask pizzaTask = new PizzaTask();
        pizzaTask.setId(delegateTask.getId());
        pizzaTask.setTaskName(delegateTask.getName());
        pizzaTask.setPizzaName(pizzaRepository.findById(1L).get().getName());
        if(delegateTask.hasVariable("processVar1") && delegateTask.hasVariable("processVar2")) {
            pizzaTask.setBusinessData1((String) delegateTask.getVariable("processVar1"));
            pizzaTask.setBusinessData2((String) delegateTask.getVariable("processVar2"));
        }
        pizzaTaskRepository.save(pizzaTask);

        delegateTask.removeVariable("processVar1");
        delegateTask.removeVariable("processVar2");

        log.info("Created PizzaTask: {}", pizzaTask);
    }
}
