package com.example.baseproject.services;

import com.example.baseproject.common.exceptions.LogicException;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface BpmService {

    HistoricVariableInstance getHistoricVariableByName(String processId, String name);

    String startProcessInstanceByKey(String processName, Map<String, Object> variables);

    void assertProcessSuccess(String processInstanceId);
    
}
