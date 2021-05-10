package com.example.demo.service;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExtensionsTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        UserTask userTask = delegateTask.getBpmnModelElementInstance();
        ExtensionElements extensionElements = userTask.getExtensionElements();
        Collection<CamundaProperty> properties = extensionElements.getElementsQuery()
                .filterByType(CamundaProperties.class)
                .singleResult()
                .getCamundaProperties();

        for (CamundaProperty property : properties) {
            String name = property.getCamundaName();
            String value = property.getCamundaValue();

            System.out.println("name: " + name + ", value: " + value);
            System.out.println("==========");
        }
    }
}
