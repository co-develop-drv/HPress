package date.iterator.compress.h;

public class HNode implements Comparable<HNode> {

    private int originLocation;

    private String word;

    // 出现次数
    private int weight;

    private HNode preNode;

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
        return Integer.compare(o.getWeight(), this.getWeight());
    }

    public HNode aggregate(final HNode o) {
        if (this.compareTo(o) == 1) {
            // 左大，标记0
            // 右小，标记1
            return coalize(o);
        } else if (this.compareTo(o) == 0) {
            if (this.originLocation == -1) {
                // 新节点与原相加节点值相同
                this.originLocation = o.getOriginLocation();
                this.word = o.word;
                return this;
            }
            return coalize(o);
        } else {
            // o 不小于右，但小于加和
            HNode l = this.leftNode;
            HNode r = this.rightNode;

            // 与右合并，左变为右的右
            // 如果三个都相等，todo 比较 ascii

            return l;
        }
    }

    private HNode coalize(final HNode o) {
        HNode up = new HNode(-1, "", this.weight + o.getWeight());
        up.setLeftNode(o); // 左大，标记0
        up.setLeftNode(this); // 右小，标记1
        return up;
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
