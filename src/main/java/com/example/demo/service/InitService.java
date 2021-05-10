package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class InitService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("assigneeList", Arrays.asList("user1", "user2", "demo", "demo"));

        log.info("assigneeList: {}", delegateExecution.getVariable("assigneeList"));
    }

}
