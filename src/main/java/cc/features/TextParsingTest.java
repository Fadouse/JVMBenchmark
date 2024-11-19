package cc.features;

public class TextParsingTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int lines = 1_000_000;
        String line = "John,Doe,30,New York";
        long startTime = System.nanoTime();
        int sumAge = 0;
        for(int i = 0; i < lines; i++) {
            String[] parts = line.split(",");
            sumAge += Integer.parseInt(parts[2]);
        }
        long endTime = System.nanoTime();
        System.out.println("文本解析测试完成，总年龄: " + sumAge + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "文本解析性能测试";
    }
}
