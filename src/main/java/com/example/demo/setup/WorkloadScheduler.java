package com.example.demo.setup;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WorkloadScheduler {

    private static final int NUMBER_OF_PROCESSES_TO_START = 10;
    private static int counter = 0;

    @Autowired
    private RuntimeService runtimeService;

//    @Scheduled(fixedRate = 30_000)
    public void run() {
        // create some more on first run
        if (counter == 0) {
            createLoad();
            createLoad();
            createLoad();
        }

        createLoad();
    }

    private void createLoad() {
        for (int i = 0; i < NUMBER_OF_PROCESSES_TO_START; i++) {
            runtimeService.startProcessInstanceByKey("DemoProcess");
            System.out.println("Started Process #" + counter++);
        }
        System.out.println("Starting next Batch of " + NUMBER_OF_PROCESSES_TO_START);
    }
}
