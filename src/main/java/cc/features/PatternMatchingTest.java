package cc.features;

/**
 * PatternMatchingTest.java
 *
 * 模式匹配性能测试（Java 16+）
 */
public class PatternMatchingTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 1_000_000;
        Object obj = "Test String";

        long startTime = System.nanoTime();
        for(int i=0;i<iterations;i++) {
            if(obj instanceof String s) {
                // 使用模式匹配
                if(!"Test String".equals(s)) {
                    throw new Exception("模式匹配失败。");
                }
            } else {
                throw new Exception("对象不是字符串。");
            }
        }
        long endTime = System.nanoTime();
        System.out.println("模式匹配测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "模式匹配性能测试（Java 16+）";
    }
}
