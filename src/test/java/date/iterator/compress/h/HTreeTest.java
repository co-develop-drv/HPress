package date.iterator.compress.h;

import org.junit.Test;

public class HTreeTest {
    @Test
    public void input(){
        String s = "根据声学模型,可以计算某一帧声学特征在某一状态上的声学分";
        HTree tree = new HTree();
        tree.input(s);
    }
}
