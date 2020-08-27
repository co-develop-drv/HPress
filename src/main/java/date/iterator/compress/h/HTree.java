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
        // todo 暂时不考虑acsii，回头再说，只出现一次的就没必要放到树里
        currentNode = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            System.out.println(nodes.get(i).getWord() + ":" + nodes.get(i).getWeight());
            HNode o = nodes.get(i);
            if (currentNode.compareTo(o) == 1) {
                currentNode = coalize(currentNode, o);
            } else if (currentNode.compareTo(o) == 0) {
                if (currentNode.getOriginLocation() == -1) {
                    // 新节点与原相加节点值相同
                    currentNode.setOriginLocation(o.getOriginLocation());
                    currentNode.setWord(o.getWord());
                    continue;
                }
                currentNode = coalize(currentNode, o);
            } else {
                // 由于输入前已经排序过，出现此种情况只能是当前节点是加和出来的，o 不小于右，但小于加和
                // 如果小于加和，就做为this的同级
                if (i + 1 < nodes.size()) {
                    HNode ol = nodes.get(++i);
                    HNode cl = coalize(o, ol);
                    if (currentNode.compareTo(cl) > 0) {
                        currentNode = coalize(currentNode, cl);
                    } else {
                        currentNode = coalize(cl, currentNode);
                    }
                } else {
                    currentNode = coalize(o, currentNode);
                }
            }
        }

        System.out.println(currentNode);
        // 最后遍历所有节点生成路径
        // 左大，标记0
        // 右小，标记1
    }

    // 前小后大
    private HNode coalize(final HNode c, final HNode o) {
        HNode up = new HNode(-1, "", c.getWeight() + o.getWeight());
        up.setLeftNode(o); // 左大，标记0
        up.setRightNode(c); // 右小，标记1
        return up;
    }

    private char[] splitWords(final String input) {
        // 分词，先不分，之后可以用 https://github.com/fxsjy/jieba
        return input.toCharArray();
    }
}
