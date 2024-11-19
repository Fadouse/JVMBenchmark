package cc.features;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CacheEfficiencyTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        System.out.println("开始执行缓存效率与CPU绑定/IO绑定操作测试");

        // 缓存效率测试
        cacheEfficiencyTest();

        // CPU绑定测试
        cpuBoundTest();

        // IO绑定测试
        ioBoundTest();

        System.out.println("缓存效率与CPU绑定/IO绑定操作测试完成");
    }

    @Override
    public String getTestName() {
        return "缓存效率与CPU绑定/IO绑定操作测试";
    }

    // 缓存效率测试
    private void cacheEfficiencyTest() {
        int size = 10_000_000;
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = i;
        }

        long startTime = System.nanoTime();
        long sum = 0;
        // 顺序访问，较高的缓存命中率
        for(int i = 0; i < size; i++) {
            sum += array[i];
        }
        long endTime = System.nanoTime();
        System.out.println("顺序访问耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");

        startTime = System.nanoTime();
        sum = 0;
        // 随机访问，较低的缓存命中率
        for(int i = 0; i < size; i++) {
            int index = (int)(Math.random() * size);
            sum += array[index];
        }
        endTime = System.nanoTime();
        System.out.println("随机访问耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    // CPU绑定测试
    private void cpuBoundTest() {
        int iterations = 100_000_000;
        long startTime = System.nanoTime();
        double result = 0.0;
        for(int i = 0; i < iterations; i++) {
            result += Math.sqrt(i);
        }
        long endTime = System.nanoTime();
        System.out.println("CPU绑定测试完成，结果: " + result + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    // IO绑定测试
    private void ioBoundTest() {
        int iterations = 1000;
        long startTime = System.nanoTime();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("io_test_output.txt"))) {
            for(int i = 0; i < iterations; i++) {
                writer.write("This is line " + i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("IO绑定测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }
}
