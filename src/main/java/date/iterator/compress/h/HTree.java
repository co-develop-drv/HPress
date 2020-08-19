package date.iterator.compress.h;

import java.util.HashMap;
import java.util.Map;

public class HTree {
    // 当前词
    private byte current;

    public void input(final String input) {
        // 分词，先不分，之后可以用 https://github.com/fxsjy/jieba
        char[] list = input.toCharArray();
        Map<Character, Integer> rates = new HashMap();
        for (char c : list) {
            if (rates.containsKey(c)) {
                rates.put(c, rates.get(c) + 1);
            } else {
                rates.put(c, 1);
            }
        }
        // 统计每个词的频率做权重
        // build tree ,相同频率且位置总是相邻的合并
    }

    private void splitWords() {

    }
}
