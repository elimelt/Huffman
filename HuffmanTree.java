
import java.io.*;
import java.util.*;

public class HuffmanTree {
    private HuffmanNode overallRoot;
    private Queue<HuffmanNode> nodeQueue;

    // given int[] counts param containing the numm occurences of the char
    // with value equal to its index, creates a huffman tree based on these 
    // frequencies. 
    public HuffmanTree(int[] count){
        this.nodeQueue = new PriorityQueue<>();
        fillQueue(count);
        while (nodeQueue.size() > 1) {
            HuffmanNode left = nodeQueue.remove();
            HuffmanNode right = nodeQueue.remove();
            nodeQueue.add(combineFirstTwo(left, right));            
        } 
        this.overallRoot = nodeQueue.remove();
    }

    public HuffmanTree(Scanner input){
        while (input.hasNextLine()) {
            int n = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
        }
    }

    public void write(PrintStream output){
        write(output, overallRoot, "");
    }

    private void write(PrintStream output, HuffmanNode root, String path) {
        if (root != null) {
            if (root.character != null){
                output.println(root.character);
                output.println(path);
            } 
            write(output, root.left, path + 0);
            write(output, root.right, path + 1);
        }
    }

    // combines the first two HuffmanNodes in our priority queue and returns a 
    // new node with their combined frequencies
    private HuffmanNode combineFirstTwo(HuffmanNode left, HuffmanNode right){
        return new HuffmanNode(null, left.count + right.count, left, right);
    }

    // fills our priority queue with HuffmanNodes corresponding to the frequency
    // data from int[] count. the character with int value i has frequency count[i]
    private void fillQueue(int[] count){
        for (int i = 0; i < count.length; i++)
            if (count[i] > 0)
                nodeQueue.add(new HuffmanNode(i, count[i]));
        nodeQueue.add(new HuffmanNode(count.length, 1));
    }

    /* 
    public void showTreeOrder() {
        showTreeOrder(overallRoot);
    }

    private void showTreeOrder(HuffmanNode root) {
        if (root != null) {
            if (root.character != null) System.out.println((char) (int) root.character);
            showTreeOrder(root.left);
            showTreeOrder(root.right);
        }
    }
    */

}
