package cc.features;

/**
 * ClassLoadingTest.java
 *
 * 动态类加载和反射性能测试
 */
public class ClassLoadingTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 50_000;
        long startTime = System.nanoTime();
        for(int i=0;i<iterations;i++) {
            // 动态加载类
            Class<?> clazz = Class.forName("java.lang.String");
            // 反射创建实例
            String str = (String) clazz.getDeclaredConstructor(String.class).newInstance("Test");
            if(!"Test".equals(str)) {
                throw new Exception("类加载或反射失败。");
            }
        }
        long endTime = System.nanoTime();
        System.out.println("动态类加载和反射测试耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "动态类加载和反射性能测试";
    }
}
