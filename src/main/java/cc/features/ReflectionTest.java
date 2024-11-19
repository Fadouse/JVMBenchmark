package cc.features;

/**
 * ReflectionTest.java
 *
 * 反射性能测试
 */
public class ReflectionTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 100_000;
        Class<?> clazz = String.class;
        java.lang.reflect.Method method = clazz.getMethod("length");
        String testString = "Reflection Test";

        long startTime = System.nanoTime();
        for(int i=0;i<iterations;i++) {
            int length = (int) method.invoke(testString);
            if(length != "Reflection Test".length()) {
                throw new Exception("反射调用失败。");
            }
        }
        long endTime = System.nanoTime();
        System.out.println("反射测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "反射性能测试";
    }
}
