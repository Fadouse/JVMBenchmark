package cc.features;

public class StartupAndJITWarmupTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int warmupIterations = 5_000_000;
        int testIterations = 10_000_000;
        long sum = 0;

        System.out.println("开始JIT热身阶段");

        // 热身阶段：执行计算任务以触发JIT优化
        long startWarmup = System.nanoTime();
        for(int i = 0; i < warmupIterations; i++) {
            sum += compute(i);
        }
        long endWarmup = System.nanoTime();
        System.out.println("JIT热身完成，热身耗时: " +
                (endWarmup - startWarmup) / 1_000_000 + " 毫秒");

        // 测试阶段：执行相同的计算任务，观察优化后的性能
        System.out.println("开始测试阶段，观察JIT优化后的性能提升");
        long startTest = System.nanoTime();
        long testSum = 0;
        for(int i = 0; i < testIterations; i++) {
            testSum += compute(i);
        }
        long endTest = System.nanoTime();
        System.out.println("测试阶段完成，结果: " + testSum + "，耗时: " +
                (endTest - startTest) / 1_000_000 + " 毫秒");

        // 总结
        System.out.println("启动时间与JIT热身测试完成");
    }

    @Override
    public String getTestName() {
        return "启动时间与JIT热身性能测试";
    }

    // 简单的计算方法，便于JIT编译器优化
    private long compute(int n) {
        long result = 0;
        for(int i = 0; i < 100; i++) {
            result += Math.sqrt(n + i);
        }
        return result;
    }
}
