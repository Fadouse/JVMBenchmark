package cc.features;

public class ExceptionHandlingTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 1_000_000;
        long startTime = System.nanoTime();
        int handledExceptions = 0;

        for(int i = 0; i < iterations; i++) {
            try {
                methodThatThrows(i);
            } catch (CustomException e) {
                handledExceptions++;
            }
        }

        long endTime = System.nanoTime();
        System.out.println("异常处理测试完成，处理异常次数: " + handledExceptions + "，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "异常处理性能测试";
    }

    private void methodThatThrows(int i) throws CustomException {
        if(i % 100 == 0) { // 每100次抛出一次异常
            throw new CustomException("异常发生在 i = " + i);
        }
    }

    // 自定义异常类
    public static class CustomException extends Exception {
        public CustomException(String message) {
            super(message);
        }
    }
}
