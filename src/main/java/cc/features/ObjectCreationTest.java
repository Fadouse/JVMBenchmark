package cc.features;

public class ObjectCreationTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;
        long startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            Object obj = new Object();
            obj.hashCode();
        }
        long endTime = System.nanoTime();
        System.out.println("对象创建速率测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "对象创建速率测试";
    }
}
