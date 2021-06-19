package com.example.baseproject.services.impl;

import com.example.baseproject.common.utils.Constants;
import com.example.baseproject.services.BpmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BpmServiceImpl implements BpmService {

    protected static final String PROCESS_RETURN_SUCCESS = "true";
    private final HistoryService historyService;
    private final RuntimeService runtimeService;

    @Override
    public HistoricVariableInstance getHistoricVariableByName(String processId, String name) {
        List<HistoricVariableInstance> vars = historyService.createHistoricVariableInstanceQuery().
                processInstanceId(processId).variableName(name).list();
        return vars.get(0);
    }

    @Override
    public String startProcessInstanceByKey(String processName, Map<String, Object> variables) {
        return runtimeService.startProcessInstanceByKey(processName, variables).getProcessInstanceId();
    }

    @Override
    public void assertProcessSuccess(String processInstanceId) {
        HistoricVariableInstance result = getHistoricVariableByName(processInstanceId, Constants.VARIABLE_TASK.SUCCESS);
        HistoricVariableInstance message = getHistoricVariableByName(processInstanceId, Constants.VARIABLE_TASK.ERROR_MESSAGE);
        Assert.isTrue(PROCESS_RETURN_SUCCESS.equalsIgnoreCase(result.getValue().toString()), message.getValue().toString());
    }

}
