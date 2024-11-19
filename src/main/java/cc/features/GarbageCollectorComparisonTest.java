package cc.features;

import java.util.ArrayList;
import java.util.List;

public class GarbageCollectorComparisonTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;
        List<byte[]> memoryHog = new ArrayList<>();

        long startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            // 分配大量内存以触发垃圾回收
            byte[] block = new byte[1024];
            memoryHog.add(block);
            if(i % 1000 == 0) {
                memoryHog.clear(); // 尝试回收内存
            }
        }
        long endTime = System.nanoTime();
        System.out.println("垃圾收集器性能比较测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "不同垃圾收集器性能比较测试";
    }
}
