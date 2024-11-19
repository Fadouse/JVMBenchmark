package cc.features;

public class StringOperationsTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000;
        String base = "TestString";
        StringBuilder sb = new StringBuilder();

        // 测试字符串连接
        long startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            sb.append(base).append(i);
        }
        String result = sb.toString();
        long endTime = System.nanoTime();
        System.out.println("字符串连接测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");

        // 测试字符串查找
        startTime = System.nanoTime();
        int count = 0;
        for(int i = 0; i < iterations; i++) {
            if(result.contains("TestString")) {
                count++;
            }
        }
        endTime = System.nanoTime();
        System.out.println("字符串查找测试完成，匹配次数: " + count + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");

        // 测试字符串分割
        startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            String[] parts = result.split("TestString");
        }
        endTime = System.nanoTime();
        System.out.println("字符串分割测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "字符串与文本处理性能测试";
    }
}
