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

    public static void output(final String path, final byte[] content) throws IOException {
        OutputStream outputStream = new FileOutputStream(path,true);
        outputStream.write(content);
        outputStream.close();
    }

    public static int logBy2(final int value) {
        if (value < 2) {
            return 0;
        }
        int result = 1;
        double loopValue = 2;
        while (loopValue < value) {
            result++;
            loopValue = Math.pow(loopValue, 2);
        }
        return result;
    }

    public static int bitLength(final int value) {
        int result = 0;
        double loopValue = 1;
        while (loopValue < value) {
            result++;
            loopValue *= 2;
        }
        return result;
    }

    public static byte[] charToByte(final char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    public static char byteToChar(final byte[] b) {
        return (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
    }
}
