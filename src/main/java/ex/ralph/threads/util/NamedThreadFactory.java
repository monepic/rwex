package ex.ralph.threads.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_NUMBER = new AtomicInteger();

    private final AtomicInteger threadNumber = new AtomicInteger();

    private final int poolNumber = POOL_NUMBER.incrementAndGet();
    private final String formatString;
    private boolean daemon;

    /**
     * formatString is formatted with <b>poolNumber</b> and <b>threadNumber</b></br>
     * example parameters:
     *
     * <code>
     * <pre>
       * "pool-%d-thread-%d"
       * "thread-%2$d-pool-%1$d"
       * </code>
     * </pre>
     *
     * @param formatString
     */
    public NamedThreadFactory(String formatString) {
        this(formatString, false);
    }

    public NamedThreadFactory(String formatString, boolean daemon) {
        this.formatString = formatString;
        this.daemon = daemon;
    }

    private ThreadGroup threadGroup() {
        return Thread.currentThread().getThreadGroup();
    }

    /*
     * see Executors.defaultThreadFactory()
     */
    @Override
    public Thread newThread(Runnable runnable) {

        Thread t = new Thread(threadGroup(), runnable,
                String.format(formatString, poolNumber, threadNumber.incrementAndGet()));

        t.setDaemon(daemon);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
