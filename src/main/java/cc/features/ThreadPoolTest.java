package cc.features;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int threadPoolSize = 100;
        int tasks = 100_000;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        long startTime = System.nanoTime();

        for(int i = 0; i < tasks; i++) {
            executor.submit(() -> {
                // 简单任务
                int a = 1 + 1;
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
        long endTime = System.nanoTime();
        System.out.println("线程池效率测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "线程池效率性能测试";
    }
}
