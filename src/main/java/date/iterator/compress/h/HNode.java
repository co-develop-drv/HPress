package date.iterator.compress.h;

import java.util.List;

public class HNode {

    private int originLocation;

    private String word;

    // 出现次数
    private int weight;

    private HNode preNode;

    // 子节点中权重较大的
    private List<HNode> leftNode;

    // 较小的
    private List<HNode> rightNode;

    public HNode(int originLocation, String word) {
        this.originLocation = originLocation;
        this.word = word;
    }

    // HTree List
    @Override
    public boolean equals(Object obj) {
        return this.word.equals(((HNode)obj).word);
    }

    public int getOriginLocation() {
        return originLocation;
    }

    public String getWord() {
        return word;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public HNode getPreNode() {
        return preNode;
    }

    public void setPreNode(HNode preNode) {
        this.preNode = preNode;
    }

    public List<HNode> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(List<HNode> leftNode) {
        this.leftNode.addAll(leftNode);
    }

    public List<HNode> getRightNode() {
        return rightNode;
    }

    public void setRightNode(List<HNode> rightNode) {
        this.rightNode.addAll(rightNode);
    }

    public void addLeftNode(HNode leftNode) {
        this.leftNode.add(leftNode);
    }

    public void addRightNode(HNode rightNode) {
        this.rightNode.add(rightNode);
    }

    public void addWeight(int i) {
        this.weight += 1;
    }
}
