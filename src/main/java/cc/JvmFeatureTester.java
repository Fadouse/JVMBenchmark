package cc; /**
 * JvmFeatureTester.java
 *
 * 这是一个用于测试JVM特性的主类。每个测试都会添加异常处理，并输出测试延迟时间。
 * JVM性能测试包括哈希表查询、各种排序算法的时间复杂度、indy测试、lambda测试、反射测试等。
 * 其余的功能通过实现 JvmTest 接口的其他类来完成。
 *
 * 适用于 JDK 17。
 */

import cc.features.*;

import java.util.ArrayList;
import java.util.List;

public class JvmFeatureTester {

    public static void main(String[] args) {
        List<JvmTest> tests = new ArrayList<>();

        // 数据结构测试
        tests.add(new HashTableTest());

        // 排序测试
        tests.add(new SortTest());

        // 垃圾回收测试
        tests.add(new GCTest());
        tests.add(new RealTimeGarbageCollectionTest());
        tests.add(new GarbageCollectorComparisonTest());

        // 类加载与卸载测试
        tests.add(new ClassLoadingTest());
        tests.add(new ClassUnloadingTest());

        // JIT 编译测试
        tests.add(new JITCompilationTest());
        tests.add(new JITCompilerOptimizationTest());
        tests.add(new StartupAndJITWarmupTest());

        // 语言特性测试
        tests.add(new PatternMatchingTest());
        tests.add(new RecordsTest());
        tests.add(new SealedClassesTest());
        tests.add(new LambdaTest());
        tests.add(new IndyTest());

        // 反射与动态代理测试
        tests.add(new ReflectionTest());
        tests.add(new DynamicProxyTest());
        tests.add(new ReflectionAndDynamicProxyTest());

        // 线程与并发测试
        tests.add(new ThreadCreationTest());
        tests.add(new SynchronizationTest());
        tests.add(new ThreadPoolTest());
        tests.add(new ThreadLocalTest());

        // 对象与内存管理测试
        tests.add(new ObjectCreationTest());
        tests.add(new EscapeAnalysisTest());
        tests.add(new MemoryUsageTest());

        // 字符串与文本操作测试
        tests.add(new StringOperationsTest());
        tests.add(new TextParsingTest());
        tests.add(new StringInterningTest());

        // 流操作测试
        tests.add(new SequentialStreamTest());
        tests.add(new ParallelStreamTest());
        tests.add(new StreamOperationsChainTest());

        // 序列化测试
        tests.add(new JavaSerializationTest());

        // 异常处理测试
        tests.add(new ExceptionHandlingTest());

        // 缓存效率测试
        tests.add(new CacheEfficiencyTest());

        // 代码分析与插桩开销测试
        tests.add(new CodeAnalysisInstrumentationOverheadTest());

        // 记录所有测试的总开始时间
        long totalStartTime = System.nanoTime();

        // 初始化成功和失败计数器
        int successCount = 0;
        int failureCount = 0;

        // 遍历每个测试，执行并记录时间
        for (JvmTest test : tests) {
            String testName = test.getTestName();
            System.out.println("开始执行测试: " + testName);
            long startTime = System.nanoTime();
            try {
                test.runTest();
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                System.out.println("测试 '" + testName + "' 完成，耗时: " + duration / 1_000_000 + " 毫秒");
                successCount++; // 增加成功计数
            } catch (Exception e) {
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                System.err.println("测试 '" + testName + "' 失败，耗时: " + duration / 1_000_000 + " 毫秒");
                failureCount++; // 增加失败计数
                e.printStackTrace();
            }
            System.out.println("--------------------------------------------------");
        }

        // 记录所有测试的总结束时间
        long totalEndTime = System.nanoTime();
        long totalDuration = totalEndTime - totalStartTime;

        // 打印所有测试的总耗时
        System.out.println("所有测试完成，总耗时: " + totalDuration / 1_000_000 + " 毫秒");

        // 打印成功和失败的统计信息
        System.out.println("成功通过的测试数: " + successCount);
        System.out.println("未通过的测试数: " + failureCount);

    }
}

