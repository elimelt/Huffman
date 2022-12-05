
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

    public void decode(BitInputStream input, PrintStream output, int eof) {
        int currChar = readNextCharacter(input);
        while (currChar != eof) {
            output.write(currChar);
            currChar = readNextCharacter(input);
        }
    }

    // overallRoot cant be null 
    private int readNextCharacter(BitInputStream input) {
        HuffmanNode curr = overallRoot;
        while (curr != null && curr.left != null && curr.right != null) {
            int next = input.readBit();
            if (next == 1)
                curr = curr.right;
            else 
                curr = curr.left;
        }
        return curr.character;
    }

    public HuffmanTree(Scanner input){
        while (input.hasNextLine()) {
            int n = Integer.parseInt(input.nextLine());
            String path = input.nextLine();
            this.overallRoot = updateTree(path, n, this.overallRoot);
        }
    }

    private HuffmanNode updateTree(String path, Integer character, HuffmanNode root){
        if (path.equals(""))
            return new HuffmanNode(character, 0);
        if (root == null) 
            root = new HuffmanNode(null, 0);
        if (path.charAt(0) == '1')
            root.right = updateTree(path.substring(1), character, root.right);
        else 
            root.left = updateTree(path.substring(1), character, root.left);
        return root;
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
