package cc.features;

/**
 * LambdaTest.java
 *
 * Lambda 表达式性能测试
 */
public class LambdaTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;
        long startTime = System.nanoTime();
        for(int i=0;i<iterations;i++) {
            // 使用 Lambda 表达式
            Runnable r = () -> {};
            r.run();
        }
        long endTime = System.nanoTime();
        System.out.println("Lambda 表达式测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "Lambda 表达式性能测试";
    }
}
