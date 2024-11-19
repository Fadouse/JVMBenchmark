package cc.features;

/**
 * JITCompilerOptimizationTest.java
 *
 * JIT 编译器优化级别性能测试
 */
public class JITCompilerOptimizationTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 50_000_000;
        long sum1 = 0;
        long sum2 = 0;

        // 方法1：简单循环，易于JIT优化
        long startTime1 = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            sum1 += computeOptimized(i);
        }
        long endTime1 = System.nanoTime();
        System.out.println("优化方法执行完成，结果: " + sum1 + "，耗时: " +
                (endTime1 - startTime1) / 1_000_000 + " 毫秒");

        // 方法2：间接调用，较难被JIT优化
        long startTime2 = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            sum2 += computeNonOptimized(i);
        }
        long endTime2 = System.nanoTime();
        System.out.println("非优化方法执行完成，结果: " + sum2 + "，耗时: " +
                (endTime2 - startTime2) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "JIT 编译器优化级别性能测试";
    }

    // 优化方法：直接调用，无额外开销
    private long computeOptimized(int n) {
        return n * n;
    }

    // 非优化方法：通过接口间接调用，增加调用开销
    private long computeNonOptimized(int n) {
        Computation computation = new ComputationImpl();
        return computation.compute(n);
    }

    // 计算接口
    interface Computation {
        long compute(int n);
    }

    // 计算实现类
    class ComputationImpl implements Computation {
        @Override
        public long compute(int n) {
            return n * n;
        }
    }
}
