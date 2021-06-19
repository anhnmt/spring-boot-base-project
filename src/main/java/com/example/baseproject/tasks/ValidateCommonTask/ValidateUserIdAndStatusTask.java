package com.example.baseproject.tasks.ValidateCommonTask;

import com.example.baseproject.common.utils.Constants;
import com.example.baseproject.common.utils.Status;
import com.example.baseproject.repositories.UserRepository;
import com.example.baseproject.tasks.ValidateTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import static com.example.baseproject.common.utils.DataUtils.getValueOrDefault;
import static com.example.baseproject.common.utils.MessageUtils.getMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateUserIdAndStatusTask extends ValidateTask {
    private final UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) {
        var userId = (Long) execution.getVariable(Constants.VARIABLE_TASK.USER_ID);
        var status = (Long) execution.getVariable(Constants.VARIABLE_TASK.STATUS);

        var isExist = userRepository.existsByUserIdAndStatus(userId, getValueOrDefault(status, Status.ACTIVE.getLong()));

        if (isExist) {
            setSuccess(execution);
        } else {
            setError(execution, getMessage("user.error.notFound.userId"));
        }
    }

}
