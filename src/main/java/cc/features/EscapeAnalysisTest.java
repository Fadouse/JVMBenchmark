package cc.features;

public class EscapeAnalysisTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;
        long startTime = System.nanoTime();
        long sum = 0;
        for(int i = 0; i < iterations; i++) {
            sum += compute();
        }
        long endTime = System.nanoTime();
        System.out.println("逃逸分析测试完成，结果: " + sum + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "逃逸分析性能测试";
    }

    private int compute() {
        class Data {
            int value = 1;
        }
        Data data = new Data();
        return data.value;
    }
}
