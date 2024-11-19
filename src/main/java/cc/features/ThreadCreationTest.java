package cc.features;

public class ThreadCreationTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int threadCount = 1000;
        long startTime = System.nanoTime();
        Thread[] threads = new Thread[threadCount];

        for(int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                // 简单任务
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        long endTime = System.nanoTime();
        System.out.println("线程创建与销毁测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "线程创建与销毁性能测试";
    }
}
