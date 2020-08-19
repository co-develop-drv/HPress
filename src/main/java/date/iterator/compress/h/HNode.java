package date.iterator.compress.h;

public class HNode {

    private int originLocation;

    private String word;

    // 出现次数
    private int weight;

    private HNode preNode;

    // 子节点中权重较大的
    private HNode leftNode;

    // 较小的
    private HNode rightNode;

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

    public HNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HNode leftNode) {
        this.leftNode = leftNode;
    }

    public HNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HNode rightNode) {
        this.rightNode = rightNode;
    }

    public void addWeight(int i) {
        this.weight += 1;
    }
}
