package cc.features;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int size = 10_000_000;
        List<Integer> list = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        long sum = list.parallelStream()
                .filter(i -> i % 2 == 0)
                .mapToLong(Integer::longValue)
                .sum();
        long endTime = System.nanoTime();
        System.out.println("并行流测试完成，结果: " + sum + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "并行流性能测试";
    }
}
