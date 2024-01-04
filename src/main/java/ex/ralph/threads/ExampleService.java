package ex.ralph.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import ex.ralph.threads.util.NamedThreadFactory;
import jakarta.annotation.PostConstruct;

@Component
public class ExampleService {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleService.class);

    private final TaskExecutor taskExecutor;

    private final ExecutorService executorService = Executors.newFixedThreadPool(2, new NamedThreadFactory("my-exec-srv-%2$d"));

    public ExampleService(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @PostConstruct
    public void doSomeThingsAsynchronously() {
        IntStream.range(0,9).forEach(
                i -> taskExecutor.execute(() -> LOG.info("I'm dealing with item #{}", i)));

        IntStream.range(0,9).forEach(
                i -> executorService.execute(() -> LOG.info("I'm doing the same, but with the executorService #{}", i)));
    }

}
