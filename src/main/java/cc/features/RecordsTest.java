package cc.features;

/**
 * RecordsTest.java
 *
 * 记录类（Records）性能测试
 */
public class RecordsTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 1_000_000;
        long startTime = System.nanoTime();
        for(int i=0;i<iterations;i++) {
            Person person = new Person("Name" + i, i);
            if(!("Name" + i).equals(person.name()) || person.age() != i) {
                throw new Exception("记录类创建失败。");
            }
        }
        long endTime = System.nanoTime();
        System.out.println("记录类（Records）测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "记录类（Records）性能测试";
    }

    // 记录类定义
    record Person(String name, int age) {}
}
