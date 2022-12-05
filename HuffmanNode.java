public class HuffmanNode implements Comparable<HuffmanNode>{
    public Integer character;
    public int count;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(Integer c, int count, HuffmanNode l, HuffmanNode r){
        this.character = c;
        this.count = count;
        this.left = l;
        this.right = r;
    }
    
    public HuffmanNode(Integer c, int count) {
        this(c, count, null, null);
    }

    public int compareTo(HuffmanNode other) {
        return this.count - other.count;
    }
}
