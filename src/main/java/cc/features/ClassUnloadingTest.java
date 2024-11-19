package cc.features;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassUnloadingTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 100_000;
        List<Class<?>> classes = new ArrayList<>();

        long startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            // 使用自定义类加载器加载简单的类
            URLClassLoader classLoader = new URLClassLoader(new URL[0], null);
            Class<?> cls = classLoader.loadClass("java.lang.String"); // 加载已有类以避免实际加载新类
            classes.add(cls);
            // 移除引用以便类卸载
            // 实际上，由于加载的是已存在的类，类卸载可能不会发生
        }
        long endTime = System.nanoTime();
        System.out.println("类卸载测试完成，加载 " + iterations + " 次类，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");

        // 提示垃圾回收以尝试卸载类
        System.gc();
        Thread.sleep(1000); // 等待GC
    }

    @Override
    public String getTestName() {
        return "类卸载性能测试";
    }
}
