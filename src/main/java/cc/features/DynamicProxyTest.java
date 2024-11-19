package cc.features;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 10_000_000;

        // 创建代理实例
        MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class<?>[]{MyInterface.class},
                new MyInvocationHandler()
        );

        long startTime = System.nanoTime();
        int sum = 0;
        for(int i = 0; i < iterations; i++) {
            sum += proxyInstance.compute(i);
        }
        long endTime = System.nanoTime();

        System.out.println("动态代理测试完成，结果: " + sum + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "动态代理性能测试";
    }

    // 定义接口
    public interface MyInterface {
        int compute(int value);
    }

    // 实现 InvocationHandler
    public static class MyInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("compute".equals(method.getName()) && args.length == 1 && args[0] instanceof Integer) {
                int val = (Integer) args[0];
                return val * val; // 简单的计算
            }
            return 0;
        }
    }
}
