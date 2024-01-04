package ex.ralph.threads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import ex.ralph.threads.util.NamedThreadFactory;

@Configuration
public class AppConfig {


    /**
     * <p>
     * This is the 'Spring proper' doc
     * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.task-execution-and-scheduling">Spring documentation</a>
     * </p>
     * <p>
     * This explains that spring would create this automatically if you didn't define the bean, and you can configure it
     * via properties in the application.yaml/.properties
     * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.task-execution-and-scheduling">Spring Boot documentation</a>
     * </p>
     *
     */
    @Bean ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setThreadFactory(new NamedThreadFactory("my-tex-%1$d"));
        return taskExecutor;
    }
}
