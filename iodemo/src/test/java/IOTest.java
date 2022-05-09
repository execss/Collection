import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class IOTest {

    /**
     * business related
     */
    @Test
    void t1() throws Exception {
        File file = new File("C:\\Intel\\AccessKey.csv");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        bufferedReader.lines().forEach(System.out::println);
    }

    /**
     * 读取文本
     * Files.readAllLines(path); 小文件
     * Files.write() 被重载以写入 byte 数组或任何 Iterable 对象（它也有 Charset
     * 选项）
     *
     * @throws IOException
     */
    @Test
    void StreamIOR() throws IOException {
        Path path = Paths.get("C:\\Intel\\AccessKey.csv");
        List<String> stringList = Files.readAllLines(path);
        stringList.stream().forEach(System.out::println);

        Files.lines(path).forEach(System.out::println);
    }

    /**
     * 写入文本
     * Files 小文件,
     * Files.write() 被重载以写入 byte 数组或任何 Iterable 对象（它也有 Charset
     * 选项）
     *
     * @throws IOException
     */
    @Test
    void StreamIOW() throws IOException {
        Files.write(Paths.get("C:\\Intel\\AccessKey.text"), Files.readAllLines(Paths.get("C:\\Intel\\AccessKey.csv")));
    }

    @Test
    void ByteIO() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Intel\\AccessKey.csv"));

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Intel\\namesss");
        byte[] b = new byte[1024];
        int len;//TODO int len= -1
        while ((len = fileInputStream.read(b)) != -1) {
            fileOutputStream.write(b, 0, len);
        }

        while ((len = fileInputStream.read()) != -1) {
            fileOutputStream.write(len);
        }

        try (
                Stream<String> input =
                        Files.lines(Paths.get("C:\\Intel\\AccessKey.csv"));
                PrintWriter output =
                        new PrintWriter("StreamInAndOut.txt")
        ) {
            input.map(String::toUpperCase)
                    .forEachOrdered(output::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void BufferedIO() {
        /**
         * 字符缓冲IO
         */
        try (BufferedReader in = new BufferedReader(
                new FileReader("C:\\Intel\\name.pdf"));
             PrintWriter out = new PrintWriter(
                     new BufferedWriter(new FileWriter("txt1.pdf")));
        ) {
//            in.lines().forEach(out::println);
            in.lines().forEach(out::write);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

        /**
         * 字节缓冲IO
         *
         */
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(
                        Files.newInputStream(new File("C:\\Intel\\name.pdf").toPath())
                );
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get("test.pdf")));
        ) {
            int dataLen = -1;
            byte[] bytes = new byte[1024];
            while ((dataLen = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, dataLen);
            }
        } catch (Exception e) {

        }


    }

    /**
     * 的任何流类，都可以通过调用 getChannel( ) 方法生成一个
     * FileChannel（文件通道）。FileChannel 的操作相当基础：操作 ByteBuffer 来用于
     * 读写，并独占式访问和锁定文件区域 (稍后将对此进行描述)。
     */
    @Test
    void StreamIOs() {
        try (
                FileChannel fc = new FileOutputStream("name")
                        .getChannel()
        ) {
            fc.write(ByteBuffer
                    .wrap("Some text ".getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
