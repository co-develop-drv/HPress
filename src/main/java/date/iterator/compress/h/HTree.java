package date.iterator.compress.h;

import java.util.*;

public class HTree {
    // 当前词
    private HNode currentNode;

    private HNode root;

    public HTree() {
//        root = new HNode(-1, "", Integer.MAX_VALUE);
        currentNode = null;
    }

    public void input(final String input) {
        char[] list = splitWords(input);

        // 统计每个词的频率做权重
        Map<Character, HNode> rates = new HashMap();
        for (int i = 0; i < list.length; i++) {
            if (rates.containsKey(list[i])) {
                rates.get(list[i]).addWeight(1);
            } else {
                rates.put(list[i], new HNode(i, String.valueOf(list[i])){{addWeight(1);}});
            }
        }

        //(n1.getWeight() == n2.getWeight()) ? 0 : ((n1.getWeight() < n2.getWeight()) ? 1 : -1)
        Comparator<HNode> order = Collections.reverseOrder((n1 ,n2) -> Integer.compare(n2.getWeight(), n1.getWeight()));

        List<HNode> nodes = new ArrayList<HNode>(rates.values()){
            // indexOf()
            @Override
            public boolean equals(Object obj) {
                // 暂时不用
                return (this == obj);
            }
        };
        nodes.sort(order);

        // todo 相同频率且位置总是相邻的合并
        // 权重相同按ACSII排列，只出现一次的就没必要放到树里
        currentNode = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            System.out.println(nodes.get(i).getWord() + ":" + nodes.get(i).getWeight());
            HNode node = nodes.get(i);
            currentNode.aggregate(node);
        }

        // 最后遍历所有节点生成路径
    }

    private char[] splitWords(final String input) {
        // 分词，先不分，之后可以用 https://github.com/fxsjy/jieba
        return input.toCharArray();
    }
}
