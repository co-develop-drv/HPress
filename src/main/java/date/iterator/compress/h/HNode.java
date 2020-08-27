package date.iterator.compress.h;

public class HNode implements Comparable<HNode> {

    private int originLocation;

    private String word;

    // 出现次数
    private int weight;

    // 子节点中权重较大的
    private HNode leftNode;

    // 较小的
    private HNode rightNode;

    private String codePath;
    private byte code;

    public HNode(int originLocation, String word) {
        this.originLocation = originLocation;
        this.word = word;
    }

    public HNode(int originLocation, String word, int weight) {
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

    public void addWeight(int i) {
        this.weight += 1;
    }

    public void setOriginLocation(int originLocation) {
        this.originLocation = originLocation;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
