package cc.features;

public class ThreadLocalTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int threadCount = 1000;
        int iterations = 10_000;
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
        long startTime = System.nanoTime();

        Thread[] threads = new Thread[threadCount];
        for(int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < iterations; j++) {
                    threadLocal.set(j);
                    Integer value = threadLocal.get();
                }
            });
            threads[i].start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        long endTime = System.nanoTime();
        System.out.println("线程局部变量性能测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "线程局部变量性能测试";
    }
}
