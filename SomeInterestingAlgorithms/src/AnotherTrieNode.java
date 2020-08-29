import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第二种字典树
 */
public class AnotherTrieNode {

    public static void main(String[] args) {
        String text = "thestoryofleetcodeandme";
        String[] words = {"story","fleet","leetcode"};
        int[][] ans = new AnotherTrieNode().indexPairs(text, words);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                System.out.println("i ： " + ans[i][j]);
            }
        }
    }

    static class TrieNode {
        // 存放数据
        private char data;

        // 是否一个字典词汇的结束
        private boolean isEndFlag = false;

        private Map<Character, TrieNode> child = new HashMap<>();

        public TrieNode(char data) {
            this.data = data;
        }
    }



    public int[][] indexPairs(String text, String[] words) {
        // 构造字典树
        TrieNode root = new TrieNode('/');

        for (String str : words) {
            insert(root, str);
        }
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            for (int j = i + 1; j <= text.length(); j++) {
                String tmp = text.substring(i, j);
                if (find(tmp, root)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j-1);
                    ans.add(list);
                }
            }
        }
        int[][] result = new int[ans.size()][];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = getArray(ans.get(i));
        }
        return result;
    }

    private int[] getArray(List<Integer> integers) {
        int[] ans = new int[integers.size()];
        for (int i= 0; i< integers.size();i++) {
            ans[i] = integers.get(i);
        }
        return ans;
    }

    private void insert(TrieNode root, String str) {
        TrieNode p = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (p.child.containsKey(c)) {
                p = p.child.get(c);
            } else {
                TrieNode trieNode = new TrieNode(c);
                p.child.put(c, trieNode);
                p = p.child.get(c);
            }
        }
        p.isEndFlag = true;
    }

    private boolean find(String destStr, TrieNode root) {
        TrieNode p = root;
        for (int i = 0; i < destStr.length(); i++) {
            char c = destStr.charAt(i);
            if (p.child.containsKey(c)) {
                p = p.child.get(c);
            } else {
                return false;
            }
        }
        return p.isEndFlag;
    }
}
