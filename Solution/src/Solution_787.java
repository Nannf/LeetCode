import java.util.*;

/**
 * @auth Nannf
 * @date 2020/10/20 15:17
 * @description: 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 * <p>
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * <p>
 * n 范围是 [1, 100]，城市标签从 0 到 n - 1
 * 航班数量范围是 [0, n * (n - 1) / 2]
 * 每个航班的格式 (src, dst, price)
 * 每个航班的价格范围是 [1, 10000]
 * k 范围是 [0, n - 1]
 * 航班没有重复，且不存在自环
 */
public class Solution_787 {
    public static void main(String[] args) {
        int[][] flights = {{0, 1, 5}, {1, 2, 5}, {0, 3, 2}, {3, 1, 2}, {1, 4, 1}, {4, 2, 1}};
        System.out.println(new Solution_787().findCheapestPrice(5, flights, 0, 2, 2));
    }


    int minTotal = Integer.MAX_VALUE;

    // 就是给定一个有向无环加权图，给定起始点，和终点，找到起始点到终点的所有路径
    // 然后再通过给定的K过滤掉那些不合法的数据
    // 再从合法的数据中找到最短的那条
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Graph graph = new Graph(n);
        // 第一步 先根据给定的数组 构造出这个有向无环加权图
        for (int i = 0; i < flights.length; i++) {
            int[] info = flights[i];
            graph.addEdge(info[0], info[1], info[2]);
        }
        List<LineInfo> trace = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];


        // 回溯超时，如果是记忆化的话，我想到的是如果到达一个序列 a->b 表示当前节点是a,下一条节点是b，如果此时到达a的路径的大小是存在的，表示是个重复的状态，无需接着往下走，
        // 当我想到这的时候，发现这个问题似乎是背包问题的一个变种，我们先用记忆化，看看是否能够解决超时的问题，如果无法解决就在试试按照背包问题的解决思路来解决这道题

        // 记忆化我们要保存三个信息 a->b 的信息，还需要保留到达a的时候的已经过路径的大小
        // 记忆化其实只要记录b就行了，而不用管是从哪个地方来的
        // key dst value : key :路径长度   value 路径大小
//        Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
        int[][][] memo = new int[n][n + 1][1];
        //找到所有的路径
        backtrace(dst, K, src, graph.adj[src], trace, visited, graph, memo);


        if (minTotal == Integer.MAX_VALUE) {
            return -1;
        }
        return minTotal;
    }

    private void backtrace(int dest, int k, int src, LinkedList<LineInfo> edgeInfo, List<LineInfo> trace,
                           boolean[][] visited, Graph graph, int[][][] memo) {
        // 如果此时这个序列中的长度 已经 大于了最终的结果值，直接跳过
        // 第一次剪枝
        if (trace.size() - 1 > k) {
            return;
        }

        int tmp = 0;
        for (LineInfo lineInfo : trace) {
            tmp += lineInfo.q;
        }
        // 第二次剪枝
        if (tmp >= minTotal) {
            return;
        }
        // 当当前处理的节点是目标节点时
        if (src == dest) {
            minTotal = Math.min(minTotal, tmp);
            return;
        }

        if (!notInMemo(src,trace,tmp,memo)) {
            return;
        }

        for (LineInfo lineInfo : edgeInfo) {
            // 如果当前的节点没有被访问过
            // 用记忆化第二次剪枝
            if (!visited[src][lineInfo.v] ) {
                visited[src][lineInfo.v] = true;
                trace.add(lineInfo);
                backtrace(dest, k, lineInfo.v, graph.adj[lineInfo.v], trace, visited, graph, memo);
                trace.remove(trace.size() - 1);
                visited[src][lineInfo.v] = false;
            }
        }
    }

    // memo[][][] 1: 目标节点 2: 路径长度 3： 路径大小
    private boolean notInMemo(int src, List<LineInfo> trace, int tmp, int[][][] memo) {
        // 先判断当前有没有到过这个节点
        for (int i = 0; i < memo[src].length; i++) {
            // 如果已经到过这个节点
            // 如果已经有路径比我短，且大小比我小的存在，那么我这个就没必要入了
            if (memo[src][i][0] != 0) {
                if (i <= trace.size() && memo[src][i][0] <= tmp) {
                    System.out.println("skip:" + trace);
                    return false;
                }
            }
        }
        memo[src][trace.size()][0] = tmp;
        return true;
    }


    static class Graph {
        // 顶点的个数
        private int v;
        private LinkedList<LineInfo>[] adj;

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int src, int dest, int q) {
            adj[src].add(new LineInfo(dest, q));
        }

    }

    static class LineInfo {
        int v;
        int q;

        public LineInfo(int v, int q) {
            this.v = v;
            this.q = q;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getQ() {
            return q;
        }

        public void setQ(int q) {
            this.q = q;
        }

        @Override
        public String toString() {
            return v + ":" + q;
        }
    }
}
