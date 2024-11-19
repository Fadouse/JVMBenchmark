package cc.features;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReflectionAndDynamicProxyTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;
        int sumReflection = 0;
        int sumProxy = 0;

        // 准备反射调用
        SampleClass sample = new SampleClass();
        Method method = SampleClass.class.getMethod("compute", int.class);

        // 反射调用测试
        long startReflection = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            sumReflection += (int) method.invoke(sample, i);
        }
        long endReflection = System.nanoTime();
        System.out.println("反射调用测试完成，结果: " + sumReflection + "，耗时: " +
                (endReflection - startReflection) / 1_000_000 + " 毫秒");

        // 准备动态代理调用
        SampleInterface proxyInstance = (SampleInterface) Proxy.newProxyInstance(
                SampleInterface.class.getClassLoader(),
                new Class<?>[]{SampleInterface.class},
                new SampleInvocationHandler()
        );

        // 动态代理调用测试
        long startProxy = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            sumProxy += proxyInstance.compute(i);
        }
        long endProxy = System.nanoTime();
        System.out.println("动态代理调用测试完成，结果: " + sumProxy + "，耗时: " +
                (endProxy - startProxy) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "反射与动态代理高负载下的性能测试";
    }

    // 目标接口
    public interface SampleInterface {
        int compute(int value);
    }

    // 实现接口的类
    public static class SampleClass implements SampleInterface {
        @Override
        public int compute(int value) {
            return value * value;
        }
    }

    // InvocationHandler实现
    public static class SampleInvocationHandler implements InvocationHandler {
        private final SampleClass target = new SampleClass();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("compute") && args.length == 1 && args[0] instanceof Integer) {
                return target.compute((Integer) args[0]);
            }
            return 0;
        }
    }
}
