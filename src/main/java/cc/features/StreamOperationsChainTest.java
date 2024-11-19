package cc.features;

import java.util.ArrayList;
import java.util.List;

public class StreamOperationsChainTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int size = 1_000_000;
        List<Integer> list = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        long result = list.stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2)
                .filter(i -> i % 3 == 0)
                .mapToLong(Integer::longValue)
                .sum();
        long endTime = System.nanoTime();
        System.out.println("流操作链测试完成，结果: " + result + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "流操作链性能测试";
    }
}
