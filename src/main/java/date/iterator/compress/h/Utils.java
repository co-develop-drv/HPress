package date.iterator.compress.h;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Utils {
    public static long ascii(final String input) {
        StringBuilder value = new StringBuilder();
        for (char c : input.toCharArray()) {
            value.insert(0, (int) c);
        }
        return Long.parseLong(value.toString());
    }

    public static void output(String path, byte[] content) throws IOException {
        OutputStream outputStream = new FileOutputStream(path,true);
        outputStream.write(content);
        outputStream.close();
    }
}
