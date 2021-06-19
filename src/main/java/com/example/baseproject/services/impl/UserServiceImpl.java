package com.example.baseproject.services.impl;

import com.example.baseproject.common.exceptions.LogicException;
import com.example.baseproject.common.utils.Constants.BPM;
import com.example.baseproject.common.utils.Constants.VARIABLE_TASK;
import com.example.baseproject.domains.request.UserRequest;
import com.example.baseproject.domains.response.Response;
import com.example.baseproject.repositories.UserRepository;
import com.example.baseproject.services.BpmService;
import com.example.baseproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.baseproject.common.utils.MessageUtils.getMessage;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BpmService bpmService;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Object> findAll() {
        var users = userRepository.findAll();

        if (ObjectUtils.isEmpty(users)) {
            return Response.badRequest("Not found all users");
        }

        return Response.ok(users);
    }

    @Override
    public ResponseEntity<Object> create(UserRequest userRequest) throws LogicException {
        Map<String, Object> variables = new HashMap<>();
        variables.put(VARIABLE_TASK.SUCCESS, Boolean.FALSE);
        variables.put(VARIABLE_TASK.ERROR_MESSAGE, getMessage("system.error"));
        variables.put(VARIABLE_TASK.COMMAND, userRequest);

        String processInstanceId = bpmService.startProcessInstanceByKey(BPM.CREATE_NEW_USER, variables);
        try {
            bpmService.assertProcessSuccess(processInstanceId);
        } catch (LogicException e) {
            e.printStackTrace();
        }

        return Response.ok();
    }
}
