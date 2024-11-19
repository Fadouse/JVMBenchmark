package cc.features;

/**
 * RealTimeGarbageCollectionTest.java
 *
 * 实时垃圾回收与低延迟应用性能测试
 */
public class RealTimeGarbageCollectionTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int allocationIterations = 5_000_000;
        int criticalPathIterations = 5_000_000;
        long sum = 0;

        // 分配和释放大量对象，触发垃圾回收
        Thread gcThread = new Thread(() -> {
            for(int i = 0; i < allocationIterations; i++) {
                byte[] data = new byte[1024]; // 每次分配1KB
                // 不持有引用，快速释放
            }
        });

        gcThread.start();

        // 同时执行关键路径任务，模拟低延迟应用
        long startCritical = System.nanoTime();
        for(int i = 0; i < criticalPathIterations; i++) {
            sum += compute(i);
        }
        long endCritical = System.nanoTime();

        gcThread.join();

        System.out.println("低延迟关键路径执行完成，结果: " + sum + "，耗时: " +
                (endCritical - startCritical) / 1_000_000 + " 毫秒");
        System.out.println("实时垃圾回收与低延迟应用性能测试完成");
    }

    @Override
    public String getTestName() {
        return "实时垃圾回收与低延迟应用性能测试";
    }

    // 关键路径方法
    private long compute(int n) {
        return n * n;
    }
}
