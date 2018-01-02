package observer;

import decorator.service.impl.stream.LowerCaseInputStream;
import org.junit.Test;

import java.io.*;

public class LowerCaseInputStreamTest {

    @Test
    public void testLowerCaseInputStream() throws IOException {
        InputStream input = new LowerCaseInputStream(new BufferedInputStream(getClass().getResourceAsStream("lowerCase.txt")));
        int read;
        while ((read = input.read()) != -1) {
            System.out.print((char)read);
        }
        input.close();
    }
}
