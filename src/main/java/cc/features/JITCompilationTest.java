package cc.features;

/**
 * JITCompilationTest.java
 *
 * JIT 编译性能测试
 */
public class JITCompilationTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000; // 减少迭代次数以适应合理的测试时间
        long startTime = System.nanoTime();
        long sum = 0;
        for(int i=0;i<iterations;i++) {
            sum += fibonacci(10);
        }
        long endTime = System.nanoTime();
        System.out.println("JIT 编译测试完成，结果: " + sum + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "JIT 编译性能测试";
    }

    // 递归计算斐波那契数
    private long fibonacci(int n) {
        if(n <= 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
