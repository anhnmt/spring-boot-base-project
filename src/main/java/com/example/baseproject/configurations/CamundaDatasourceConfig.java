package com.example.baseproject.configurations;

import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.engine.spring.SpringProcessEngineServicesConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@Import({SpringProcessEngineServicesConfiguration.class})
public class CamundaDatasourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.camunda")
    public DataSource camundaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager bpmTransactionManager(@Qualifier("camundaDataSource") DataSource bpmDataSource) {
        return new DataSourceTransactionManager(bpmDataSource);
    }

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(@Qualifier("camundaDataSource") DataSource bpmDataSource, @Qualifier("bpmTransactionManager") PlatformTransactionManager bpmTransactionManager, JobExecutor jobExecutor) throws IOException {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
        config.setDataSource(bpmDataSource);
        config.setTransactionManager(bpmTransactionManager);
        config.setDatabaseSchemaUpdate("true");
        config.setHistory("audit");
        config.setJobExecutorActivate(true);
        config.setMetricsEnabled(false);
        config.setJobExecutor(jobExecutor);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        config.setDeploymentResources(resolver.getResources("classpath:/bpmn/*.bpmn"));
        return config;
    }

}
