package APP.test;

import java.io.FileWriter;
import java.io.IOException;

public class fileTest {
    public static void main(String[] args) {
        String filePath = "output.txt"; // 文件路径
        try (FileWriter writer = new FileWriter(filePath, true)) { // true 表示以追加模式打开文件
            for (int i = 0; i < 100; i++) {
                writer.write("Data line " + i + "\n"); // 写入数据
                writer.flush(); // 确保数据即时写入文件
                try {
                    Thread.sleep(1000); // 模拟每秒写入一次数据
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted, Failed to complete operation");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
