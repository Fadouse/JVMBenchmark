package cc.features;

/**
 * IndyTest.java
 *
 * invokedynamic 指令性能测试
 */
public class IndyTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;
        long startTime = System.nanoTime();
        for(int i=0;i<iterations;i++) {
            // 使用方法引用，底层使用 invokedynamic
            Runnable r = this::dummyMethod;
            r.run();
        }
        long endTime = System.nanoTime();
        System.out.println("invokedynamic (方法引用) 测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "invokedynamic 指令性能测试";
    }

    // 示例方法
    private void dummyMethod() {
        // 空方法
    }
}
