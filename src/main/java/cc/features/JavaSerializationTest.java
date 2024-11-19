package cc.features;

import java.io.*;

public class JavaSerializationTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 100_000;
        TestObject obj = new TestObject("Test", 123, true);

        // 序列化
        long startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            oos.close();
            bos.close();
        }
        long endTime = System.nanoTime();
        System.out.println("Java 内置序列化测试完成，序列化耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");

        // 反序列化
        startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            // 模拟反序列化
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            TestObject deserializedObj = (TestObject) ois.readObject();
            ois.close();
            bis.close();
            oos.close();
            bos.close();
        }
        endTime = System.nanoTime();
        System.out.println("Java 内置序列化测试完成，反序列化耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "Java 内置序列化性能测试";
    }

    // 测试对象
    public static class TestObject implements Serializable {
        private static final long serialVersionUID = 1L;
        String name;
        int value;
        boolean active;

        public TestObject(String name, int value, boolean active) {
            this.name = name;
            this.value = value;
            this.active = active;
        }
    }
}
