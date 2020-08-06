package date.iterator.compress.h;

public class HNode {
    private int originLocation;

    // 出现次数
    private int weight;

    private HNode preNode;

    // 子节点中权重较大的
    private HNode leftNode;

    // 较小的
    private HNode rightNode;

    private String word;
}
