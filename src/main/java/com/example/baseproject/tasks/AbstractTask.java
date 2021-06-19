package com.example.baseproject.tasks;

import com.example.baseproject.common.contexts.ApplicationContextProvider;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public abstract class AbstractTask implements JavaDelegate {

    protected <T> T getBean(Class<T> objClass) {
        return ApplicationContextProvider.getApplicationContext().getBean(objClass);
    }

}
