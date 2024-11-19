package cc.features;

/**
 * SealedClassesTest.java
 *
 * 密封类（Sealed Classes）性能测试
 */
public class SealedClassesTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        int iterations = 1_000_000;
        long startTime = System.nanoTime();
        for(int i=0;i<iterations;i++) {
            Shape shape = i % 2 == 0 ? new Circle(1.0) : new Rectangle(1.0, 2.0);
            double area;
            if (shape instanceof Circle c) {
                area = Math.PI * c.radius() * c.radius();
            } else {
                Rectangle r = (Rectangle) shape;
                area = r.length() * r.width();
            }
            // 简单验证面积计算
            if(i % 2 == 0 && Math.abs(area - (Math.PI * 1.0 * 1.0)) > 0.0001) {
                throw new Exception("密封类模式匹配失败。");
            }
            if(i % 2 != 0 && Math.abs(area - (1.0 * 2.0)) > 0.0001) {
                throw new Exception("密封类模式匹配失败。");
            }
        }
        long endTime = System.nanoTime();
        System.out.println("密封类（Sealed Classes）测试完成，耗时: " + (endTime - startTime) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "密封类（Sealed Classes）性能测试";
    }

    // 密封类定义
    sealed interface Shape permits Circle, Rectangle {}

    final class Circle implements Shape {
        private final double radius;
        public Circle(double radius) { this.radius = radius; }
        public double radius() { return radius; }
    }

    final class Rectangle implements Shape {
        private final double length;
        private final double width;
        public Rectangle(double length, double width) { this.length = length; this.width = width; }
        public double length() { return length; }
        public double width() { return width; }
    }
}
