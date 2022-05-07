import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class IOTest {

    /**
     * business related
     */
    @Test
    void  t1()throws Exception{
        File file= new File("C:\\Intel\\AccessKey.csv");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        bufferedReader.lines().forEach(System.out::println);
    }

    @Test
    void StreamIO() throws IOException {
        Path path = Paths.get("C:\\Intel\\AccessKey.csv");
        List<String> stringList = Files.readAllLines(path);
        stringList.stream().forEach(System.out::println);

        Files.lines(path).forEach(System.out::println);
    }
}
