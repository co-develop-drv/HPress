package date.iterator.compress.h;

import java.io.IOException;
import java.util.*;

public class HTree {

    private HNode root;

    private int realNodeCount = 0;

    // 词编码映射
    private Map<String, Byte> wordTable = new HashMap();

    public HTree() {
//        root = new HNode(-1, "", Integer.MAX_VALUE);
    }

    public void input(final String input) {
        char[] list = splitWords(input);
        realNodeCount = list.length;

        Map<Character, HNode> rates = calculateRates(list);

        Comparator<HNode> order = Collections.reverseOrder((n1 ,n2) -> Integer.compare(n2.getWeight(), n1.getWeight()));

        List<HNode> nodes = new ArrayList<HNode>(rates.values()){
            @Override // indexOf() // 暂时不用
            public boolean equals(Object obj) {return (this == obj);}
        };
        nodes.sort(order);

        // todo 相同频率且位置总是相邻的合并
        // todo 暂时不考虑acsii，回头再说，只出现一次的就没必要放到树里
        HNode currentNode = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            System.out.println(nodes.get(i).getWord() + ":" + nodes.get(i).getWeight());
            HNode o = nodes.get(i);
            if (currentNode.compareTo(o) == 1) {
                currentNode = coalize(currentNode, o);
            } else if (currentNode.compareTo(o) == 0) {
                if (currentNode.getOriginLocations().isEmpty()) {
                    // 新节点与原相加节点值相同
                    currentNode.addOriginLocation(o.getOriginLocations());
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
        root = currentNode;

        // 最后遍历所有节点生成路径
        // 左大，标记0
        // 右小，标记1
        output(currentNode);
    }

    private void output(final HNode currentNode) {
        byte[] sequence = new byte[realNodeCount];
        // 前序遍历 先左0
        byte[] result = toBytes("0", currentNode, sequence);
        System.out.println(result);

        // wordTable 和 result 写文件
        Iterator<String> keyIterator = wordTable.keySet().iterator();
        String key = keyIterator.next();
        String table = "";
        table += key + ":" + wordTable.get(key);
        while (keyIterator.hasNext()) {
            key = keyIterator.next();
            table +=  "," + key + ":" + wordTable.get(key);
        }
        String dir = "D:\\Github\\HPress\\src\\test\\resources";
        try {
            Utils.output(dir + "\\tree.txt", table.getBytes());
            Utils.output(dir + "\\aaa.txt", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 递左归右 预防根碰巧有值
    private byte[] toBytes(String code, final HNode node, final byte[] sequence){
        recordWordTable(code, node, sequence);
        if (node.getLeftNode() != null) {
            code += "0";
            recordWordTable(code, node.getLeftNode(), sequence);
            toBytes(code, node.getLeftNode(), sequence);
        }
        if (node.getRightNode() != null) {
            code += "1";
            recordWordTable(code, node.getRightNode(), sequence);
            toBytes(code, node.getRightNode(), sequence);
        }
        return sequence;
    }

    private void recordWordTable(final String code, final HNode node, final byte[] sequence) {
        if (!node.getOriginLocations().isEmpty()) {
            byte wordCode = Byte.parseByte(code);
            wordTable.put(node.getWord(), wordCode);
            for (Integer location : node.getOriginLocations()) {
                sequence[location] = wordCode;
            }
        }
    }

    // 前小后大
    private HNode coalize(final HNode c, final HNode o) {
        HNode up = new HNode(-1, "", c.getWeight() + o.getWeight());
        up.setLeftNode(o); // 左大，标记0
        up.setRightNode(c); // 右小，标记1
        return up;
    }

    // 统计每个词的频率做权重
    private Map<Character, HNode> calculateRates(char[] list) {
        Map<Character, HNode> rates = new HashMap();
        for (int i = 0; i < list.length; i++) {
            if (rates.containsKey(list[i])) {
                rates.get(list[i]).addWeight(1).addOriginLocation(i);
            } else {
                rates.put(list[i], new HNode(i, String.valueOf(list[i])){{addWeight(1);}});
            }
        }
        return rates;
    }

    private char[] splitWords(final String input) {
        // 分词，先不分，之后可以用 https://github.com/fxsjy/jieba
        return input.toCharArray();
    }
}
