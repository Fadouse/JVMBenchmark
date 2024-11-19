package cc.features;

import java.util.ArrayList;
import java.util.List;

public class MemoryUsageTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 1_000_000;
        List<MemoryObject> objects = new ArrayList<>();

        System.out.println("开始分配对象以分析内存占用");

        long startAllocation = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            objects.add(new MemoryObject(i, "Object" + i));
            if(i % 100_000 == 0) {
                System.out.println("已分配 " + i + " 个对象");
            }
        }
        long endAllocation = System.nanoTime();
        System.out.println("对象分配完成，耗时: " +
                (endAllocation - startAllocation) / 1_000_000 + " 毫秒");

        // 触发垃圾回收
        System.out.println("触发垃圾回收");
        objects = null;
        System.gc();
        Thread.sleep(2000); // 等待GC完成

        // 分配更多对象以观察内存释放效果
        List<MemoryObject> newObjects = new ArrayList<>();
        long startNewAllocation = System.nanoTime();
        for(int i = 0; i < iterations / 2; i++) {
            newObjects.add(new MemoryObject(i, "NewObject" + i));
        }
        long endNewAllocation = System.nanoTime();
        System.out.println("新对象分配完成，耗时: " +
                (endNewAllocation - startNewAllocation) / 1_000_000 + " 毫秒");

        // 保持引用以防止垃圾回收
        System.out.println("内存占用分析测试完成");
    }

    @Override
    public String getTestName() {
        return "内存占用分析测试";
    }

    // 测试对象类
    public static class MemoryObject {
        private int id;
        private String name;
        private byte[] data;

        public MemoryObject(int id, String name) {
            this.id = id;
            this.name = name;
            this.data = new byte[1024]; // 分配一定大小的内存
        }

        // Getter 和 Setter 可以根据需要添加
    }
}
