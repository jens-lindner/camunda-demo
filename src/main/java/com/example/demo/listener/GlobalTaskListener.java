package com.example.demo.listener;

import com.example.demo.persistence.PizzaRepository;
import com.example.demo.persistence.PizzaTaskRepository;
import com.example.demo.persistence.model.PizzaTask;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.spring.boot.starter.event.ExecutionEvent;
import org.camunda.bpm.spring.boot.starter.event.TaskEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * @see https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/the-spring-event-bridge/
 */
@Component
@Slf4j
class GlobalTaskListener {

  @Autowired
  private PizzaTaskRepository pizzaTaskRepository;

  @Autowired
  private PizzaRepository pizzaRepository;

  @EventListener(condition="#taskDelegate.eventName=='complete'")
  public void onComplete(DelegateTask taskDelegate) {
    pizzaTaskRepository.deleteById(taskDelegate.getId());
    log.info("Completed PizzaTask with id: {}", taskDelegate.getId());
  }

  @EventListener(condition="#taskDelegate.eventName=='create'")
  public void onCreate(DelegateTask taskDelegate) {
    PizzaTask pizzaTask = new PizzaTask();
    pizzaTask.setId(taskDelegate.getId());
    pizzaTask.setTaskName(taskDelegate.getName());
    pizzaTask.setPizzaName(pizzaRepository.findById(1L).get().getName());
    if(taskDelegate.hasVariable("processVar1") && taskDelegate.hasVariable("processVar2")) {
      pizzaTask.setBusinessData1((String) taskDelegate.getVariable("processVar1"));
      pizzaTask.setBusinessData2((String) taskDelegate.getVariable("processVar2"));
    }
    pizzaTaskRepository.save(pizzaTask);

    taskDelegate.removeVariable("processVar1");
    taskDelegate.removeVariable("processVar2");

    log.info("Created PizzaTask: {}", pizzaTask);
  }

}
