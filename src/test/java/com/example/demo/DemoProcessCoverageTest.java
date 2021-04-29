package com.example.demo;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DemoProcessCoverageTest {


    @ClassRule
    @Rule
    public static TestCoverageProcessEngineRule classRule = TestCoverageProcessEngineRuleBuilder.create().build();

    @Test
    @Deployment(resources = "demo_process.bpmn")
    public void testMonday() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(ProcessVariables.weekday, "monday");
        classRule.getRuntimeService().startProcessInstanceByKey("DemoProcess", variables);
    }

    @Test
    @Deployment(resources = "demo_process.bpmn")
    public void testSaturday() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(ProcessVariables.weekday, "saturday");
        classRule.getRuntimeService().startProcessInstanceByKey("DemoProcess", variables);
    }
}