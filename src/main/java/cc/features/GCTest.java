package cc.features;

import java.util.ArrayList;
import java.util.List;

/**
 * GCTest.java
 *
 * 垃圾回收性能测试
 */
public class GCTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 100_000;
        List<byte[]> memoryConsumers = new ArrayList<>();

        long startTime = System.nanoTime();
        for(int i=0;i<iterations;i++) {
            // 分配短期对象
            memoryConsumers.add(new byte[1024]); // 1KB
            if(i % 10_000 == 0) {
                System.gc(); // 手动触发垃圾回收
            }
        }
        long endTime = System.nanoTime();

        // 清理
        memoryConsumers.clear();
        System.gc();

        System.out.println("垃圾回收测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "垃圾回收性能测试";
    }
}
