package cc.features;

/**
 * JvmTest 接口
 * 所有测试类需要实现这个接口
 */
public interface JvmTest {
    /**
     * 执行测试
     *
     * @throws Exception 如果测试过程中发生任何异常
     */
    void runTest() throws Exception;

    /**
     * 获取测试名称
     *
     * @return 测试名称
     */
    String getTestName();
}
