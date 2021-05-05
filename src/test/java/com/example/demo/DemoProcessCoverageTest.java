package com.example.demo;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;


public class DemoProcessCoverageTest {

    @ClassRule
    @Rule
    public static TestCoverageProcessEngineRule classRule = TestCoverageProcessEngineRuleBuilder.create().build();

    // workaround to make Spring Beans available. Should be solved using a @SpringBootTest
    @Before
    public void initSpringBeans() {
        Mocks.register("selectFoodService", new SelectFoodService());
        Mocks.register("selectPizzaToppingService", new SelectPizzaToppingService());
        Mocks.register("selectDrinkService", new SelectDrinkService());
        Mocks.register("validationService", new ValidationService());
        Mocks.register("orderService", new OrderService());
    }

    @Test
    @Deployment(resources = "demo_process.bpmn")
    @Ignore // disabled due to external task
    public void testMonday() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(ProcessVariables.weekday, "monday");
        ProcessInstance processInstance = classRule.getRuntimeService().startProcessInstanceByKey("DemoProcess", variables);

        assertThat(processInstance).isEnded()
                .hasPassed("Activity_14omuvf" // Select Food Activity
//                        ,"IdThatDoesntExist" // uncommenting will make test fail
                ).hasVariables(ProcessVariables.weekday, ProcessVariables.selectedFood);
    }

    @Test
    @Deployment(resources = "demo_process.bpmn")
    public void testSaturday() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(ProcessVariables.weekday, "saturday");
        classRule.getRuntimeService().startProcessInstanceByKey("DemoProcess", variables);
    }

    @Test
    @Deployment(resources = "demo_process.bpmn")
    public void testUnreachablePath() {
        ProcessInstance processInstance = classRule.getRuntimeService().createProcessInstanceByKey("DemoProcess")
                .startBeforeActivity("unreachable_task")
                .execute();

        assertThat(processInstance).hasPassed("unreachable_task");
    }

}