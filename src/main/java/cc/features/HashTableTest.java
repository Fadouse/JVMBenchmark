package cc.features;

/**
 * HashTableTest.java
 *
 * 哈希表查询性能测试
 */
public class HashTableTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        // 示例：使用Java的HashMap进行大量查询测试
        java.util.HashMap<Integer, String> hashMap = new java.util.HashMap<>();
        int size = 1_000_000;
        for (int i = 0; i < size; i++) {
            hashMap.put(i, "Value" + i);
        }

        // 随机查询
        int queries = 100_000;
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < queries; i++) {
            int key = rand.nextInt(size);
            String value = hashMap.get(key);
            if (value == null) {
                throw new Exception("HashMap 查询失败，键: " + key);
            }
        }
    }

    @Override
    public String getTestName() {
        return "HashTable 查询性能测试";
    }
}
