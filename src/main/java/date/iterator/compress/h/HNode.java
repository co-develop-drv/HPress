package date.iterator.compress.h;

import java.util.LinkedList;
import java.util.List;

public class HNode implements Comparable<HNode> {

    private List<Integer> originLocations = new LinkedList<>();

    @Deprecated
//    private String word;

    private byte[] word;

    // 出现次数
    private int weight;

    // 子节点中权重较大的
    private HNode leftNode;

    // 较小的
    private HNode rightNode;

    private String codePath;
    private byte code;

    public HNode(int originLocation, byte[] word) {
        if (originLocation != -1) {
            this.originLocations.add(originLocation);
        }
        this.word = word;
    }

    public HNode(int originLocation, byte[] word, int weight) {
        this(originLocation, word);
        this.weight = weight;
    }

    // HTree List
    @Override
    public boolean equals(Object obj) {
        return this.word.equals(((HNode)obj).word);
    }

    @Override
    public int compareTo(HNode o) {
        // 前大
        return Integer.compare(o.getWeight(), this.getWeight());
    }

    public List<Integer> getOriginLocations() {
        return originLocations;
    }

    public byte[] getWord() {
        return word;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public HNode getLeftNode() {
        return leftNode;
    }

    public HNode setLeftNode(HNode leftNode) {
        this.leftNode = leftNode;
        return this;
    }

    public HNode getRightNode() {
        return rightNode;
    }

    public HNode setRightNode(HNode rightNode) {
        this.rightNode = rightNode;
        return this;
    }

    public HNode addWeight(int i) {
        this.weight += 1;
        return this;
    }

    public void addOriginLocation(int originLocation) {
        if (originLocation != -1) {
            this.originLocations.add(originLocation);
        }
    }

    public void addOriginLocation(List<Integer> originLocations) {
        this.originLocations.addAll(originLocations);
    }

    public void setWord(byte[] word) {
        this.word = word;
    }
}
