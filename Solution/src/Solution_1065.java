import java.util.*;

/**
 * 字典树
 *
 * "aaabaabababbaaabaabaaababaaaab"
 * ["abb","baba","aababb","baaaaa","aabba"]
 */

public class Solution_1065 {
    static class TrieNode {
        // 存储数据节点
        private char data;

        // 每层的节点都有26个子节点
        private TrieNode[] p = new TrieNode[26];

        private boolean isEndFlag = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        String text = "aaabaabababbaaabaabaaababaaaab";
        String[] words = {"abb","baba","aababb","baaaaa","aabba"};
        int[][] ans = new Solution_1065().indexPairs(text, words);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                System.out.println("i ： " + ans[i][j]);
            }
        }
    }

    public int[][] indexPairs(String text, String[] words) {
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        int totalNum = 0;
        // 无意义的字符串
        TrieNode root = new TrieNode('/');

        for (String str : words) {
            insert(root, str, 0);
        }
        for (int i = 0; i <= text.length() - 1; i++) {
            for (int j = i + 1; j <= text.length(); j++) {
                if (find(root, text.substring(i, j))) {
//                    System.out.println(text.substring(i, j));
                    totalNum++;
                    if (map.containsKey(i)) {
                        map.get(i).add(j - 1);
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(j - 1);
                        map.put(i, list);
                    }
                }
            }
        }
        int[][] ans = new int[totalNum][];
        int cnt = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            for (int i : entry.getValue()) {
                ans[cnt++] = new int[] {entry.getKey(),i};
            }
        }
        return ans;

    }

    private int[] getArray(List<Integer> value) {
        int[] result = new int[value.size()];
        for (int i = 0; i < value.size(); i++) {
            result[i] = value.get(i);
        }
        return result;
    }

    /**
     * 比较的逻辑是一层一层比
     *
     * @param root
     * @param str
     * @return
     */
    private boolean find(TrieNode root, String str) {
        TrieNode p = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = c - 'a';
            // 如果你的字符串比我的最长的字典还长，直接返回未匹配
            if (p.p[index] == null) {
                return false;
            }
            if (p.p[index].data == c) {
                p = p.p[index];
            } else {
                return false;
            }
        }
        // 如果匹配完成之后，看看最后的节点是否是结束节点
        return p.isEndFlag;
    }

    /**
     * 给定一个字典树，我们要插入一个字符串的顺序是什么样子的呢？
     * 插入是从根节点，一级一级加的，这个是可以用递归吗
     *
     * @param root
     * @param str
     */
    private void insert(TrieNode root, String str, int i) {
        if (i == str.length()) {
            root.isEndFlag = true;
            return;
        }
        int index = str.charAt(i) - 'a';
        if (root.p[index] == null) {
            TrieNode node = new TrieNode(str.charAt(i));
            root.p[index] = node;
            insert(node, str, i + 1);
        } else {
            insert(root.p[index], str, i + 1);
        }
    }
}
