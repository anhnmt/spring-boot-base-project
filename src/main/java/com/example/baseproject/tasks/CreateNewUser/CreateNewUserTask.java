package com.example.baseproject.tasks.CreateNewUser;

import com.example.baseproject.tasks.ValidateTask;
import com.example.baseproject.common.mappers.UserMapper;
import com.example.baseproject.common.utils.Constants;
import com.example.baseproject.common.utils.Status;
import com.example.baseproject.domains.request.UserRequest;
import com.example.baseproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateNewUserTask extends ValidateTask {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void execute(DelegateExecution execution) {
        var command = (UserRequest) execution.getVariable(Constants.VARIABLE_TASK.COMMAND);

        var user = userMapper.convertToUser(command);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }
    
}
