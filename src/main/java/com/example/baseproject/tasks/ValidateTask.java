package com.example.baseproject.tasks;

import com.example.baseproject.common.utils.Constants;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public abstract class ValidateTask extends AbstractTask {

    protected void setSuccess(DelegateExecution execution) {
        execution.setVariable(Constants.VARIABLE_TASK.SUCCESS, Boolean.TRUE);
    }

    protected void setError(DelegateExecution execution, String message) {
        execution.setVariable(Constants.VARIABLE_TASK.SUCCESS, Boolean.FALSE);
        execution.setVariable(Constants.VARIABLE_TASK.ERROR_MESSAGE, message);
    }

}
