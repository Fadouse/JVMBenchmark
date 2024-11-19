package cc.features;

/**
 * CodeAnalysisInstrumentationOverheadTest.java
 *
 * 代码分析与仪器化开销性能测试
 */
public class CodeAnalysisInstrumentationOverheadTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;
        long sumDirect = 0;
        long sumReflection = 0;
        long sumLogging = 0;

        // 直接调用方法
        long startDirect = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            sumDirect += computeDirect(i);
        }
        long endDirect = System.nanoTime();
        System.out.println("直接调用方法执行完成，结果: " + sumDirect + "，耗时: " +
                (endDirect - startDirect) / 1_000_000 + " 毫秒");

        // 反射调用方法
        java.lang.reflect.Method method = SampleClass.class.getMethod("compute", int.class);
        SampleClass sample = new SampleClass();
        long startReflection = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            sumReflection += (int) method.invoke(sample, i);
        }
        long endReflection = System.nanoTime();
        System.out.println("反射调用方法执行完成，结果: " + sumReflection + "，耗时: " +
                (endReflection - startReflection) / 1_000_000 + " 毫秒");

        // 带日志记录的调用方法
        long startLogging = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            sumLogging += computeWithLogging(i);
        }
        long endLogging = System.nanoTime();
        System.out.println("带日志记录的方法执行完成，结果: " + sumLogging + "，耗时: " +
                (endLogging - startLogging) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "代码分析与仪器化开销性能测试";
    }

    // 直接调用的方法
    private int computeDirect(int n) {
        return n * n;
    }

    // 带日志记录的方法
    private int computeWithLogging(int n) {
        // 简单的日志记录，模拟仪器化开销
        // 在实际应用中，日志记录可能使用更复杂的框架
        // 此处为了性能影响，减少日志内容
        if(n % 1_000_000 == 0) {
            System.out.println("Logging at n = " + n);
        }
        return n * n;
    }

    // 测试类
    public static class SampleClass {
        public int compute(int n) {
            return n * n;
        }
    }
}
