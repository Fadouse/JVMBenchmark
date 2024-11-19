package cc.features;


import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;
        Object lock = new Object();
        ReentrantLock reentrantLock = new ReentrantLock();
        long startTime, endTime;

        // synchronized 块
        startTime = System.nanoTime();
        int counter = 0;
        for(int i = 0; i < iterations; i++) {
            synchronized(lock) {
                counter++;
            }
        }
        if(counter != iterations) {
            throw new RuntimeException("synchronized 遍历出错! (expected: " + iterations + ", actual: " + counter + ")");
        }
        endTime = System.nanoTime();
        System.out.println("synchronized 耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");

        // ReentrantLock
        startTime = System.nanoTime();
        counter = 0;
        for(int i = 0; i < iterations; i++) {
            reentrantLock.lock();
            try {
                counter++;
            } finally {
                reentrantLock.unlock();
            }
        }
        if(counter != iterations) {
            throw new RuntimeException("ReentrantLock 遍历出错! (expected: " + iterations + ", actual: " + counter + ")");
        }
        endTime = System.nanoTime();
        System.out.println("ReentrantLock 耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "同步机制性能测试";
    }
}
