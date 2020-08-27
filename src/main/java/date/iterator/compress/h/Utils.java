package date.iterator.compress.h;

public class Utils {
    public static long ascii(final String input) {
        StringBuilder value = new StringBuilder();
        for (char c : input.toCharArray()) {
            value.insert(0, (int) c);
        }
        return Long.parseLong(value.toString());
    }
}
