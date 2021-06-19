package com.example.baseproject.tasks.CreateNewUser;

import com.example.baseproject.common.utils.Constants;
import com.example.baseproject.domains.request.UserRequest;
import com.example.baseproject.tasks.ValidateTask;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static com.example.baseproject.common.utils.MessageUtils.getMessage;

@Slf4j
@Component
public class CreateNewUserCommonTask extends ValidateTask {

    @Override
    public void execute(DelegateExecution execution) {
        var command = (UserRequest) execution.getVariable(Constants.VARIABLE_TASK.COMMAND);
        Assert.notNull(command, getMessage("system.error.empty", "UserRequest"));

        Assert.notNull(command.getName(), getMessage("user.error.empty.name"));
        Assert.notNull(command.getEmail(), getMessage("user.error.empty.email"));
        Assert.notNull(command.getPassword(), getMessage("user.error.empty.password"));
    }

}
