package cc.features;

public class StringInterningTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 100_000;
        String base = "TestString";

        // 不使用字符串驻留
        long startNoIntern = System.nanoTime();
        String[] stringsNoIntern = new String[iterations];
        for(int i = 0; i < iterations; i++) {
            stringsNoIntern[i] = new String(base + i);
        }
        long endNoIntern = System.nanoTime();
        System.out.println("不使用字符串驻留创建 " + iterations + " 个字符串，耗时: " +
                (endNoIntern - startNoIntern) / 1_000_000 + " 毫秒");

        // 使用字符串驻留
        long startIntern = System.nanoTime();
        String[] stringsIntern = new String[iterations];
        for(int i = 0; i < iterations; i++) {
            stringsIntern[i] = (base + i).intern();
        }
        long endIntern = System.nanoTime();
        System.out.println("使用字符串驻留创建 " + iterations + " 个字符串，耗时: " +
                (endIntern - startIntern) / 1_000_000 + " 毫秒");

        // 测试字符串访问速度
        long sum = 0;
        long startAccess = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            if(stringsIntern[i].equals(base + i)) {
                sum += i;
            }
        }
        long endAccess = System.nanoTime();
        System.out.println("字符串访问测试完成，结果: " + sum + "，耗时: " +
                (endAccess - startAccess) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "字符串驻留与缓存机制性能测试";
    }
}

