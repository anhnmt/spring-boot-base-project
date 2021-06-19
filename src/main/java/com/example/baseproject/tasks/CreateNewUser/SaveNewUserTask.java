package com.example.baseproject.tasks.CreateNewUser;

import com.example.baseproject.common.mappers.UserMapper;
import com.example.baseproject.common.utils.Constants.VARIABLE_TASK;
import com.example.baseproject.common.utils.Status;
import com.example.baseproject.domains.request.UserRequest;
import com.example.baseproject.repositories.UserRepository;
import com.example.baseproject.tasks.ValidateTask;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static com.example.baseproject.common.utils.DataUtils.variableToObject;
import static com.example.baseproject.common.utils.MessageUtils.getMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveNewUserTask extends ValidateTask {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void execute(DelegateExecution execution) {
        StringValue command = execution.getVariableTyped(VARIABLE_TASK.COMMAND);
        var userRequest = variableToObject(command, UserRequest.class);
        Assert.notNull(userRequest, getMessage("system.error.empty", "UserRequest"));

        var user = userMapper.convertToUser(userRequest);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

}
