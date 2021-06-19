package com.example.baseproject.common.contexts;

import org.springframework.context.ApplicationContext;

public class ApplicationContextProvider {
    private static ApplicationContext appContext;

    public static ApplicationContext getApplicationContext() {
        return appContext;
    }

    public static void setApplicationContext(ApplicationContext ctx) {
        appContext = ctx;
    }
}
