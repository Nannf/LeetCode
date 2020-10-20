import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @auth Nannf
 * @date 2020/10/20 15:17
 * @description:
 *
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 *
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
 *
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 *
 *输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 *
 * n 范围是 [1, 100]，城市标签从 0 到 n - 1
 * 航班数量范围是 [0, n * (n - 1) / 2]
 * 每个航班的格式 (src, dst, price)
 * 每个航班的价格范围是 [1, 10000]
 * k 范围是 [0, n - 1]
 * 航班没有重复，且不存在自环
 *
 *
 */
public class Solution_787 {
    public static void main(String[] args) {
        int[][] flights = {{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},{1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1},{8,7,3},{1,2,6},{4,1,5},{5,2,4},{1,9,1},{7,8,10},{0,4,2},{7,2,8}};
        System.out.println(new Solution_787().findCheapestPrice(10,flights,6,0,7));
    }



    // 就是给定一个有向无环加权图，给定起始点，和终点，找到起始点到终点的所有路径
    // 然后再通过给定的K过滤掉那些不合法的数据
    // 再从合法的数据中找到最短的那条
    public  int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Graph graph = new Graph(n);
        // 第一步 先根据给定的数组 构造出这个有向无环加权图
        for (int i = 0; i < flights.length; i++) {
            int[] info = flights[i];
            graph.addEdge(info[0], info[1], info[2]);
        }
        List<List<LineInfo>> ans = new ArrayList<>();
        List<LineInfo> trace = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];

        //找到所有的路径
        backtrace(dst, K, src, graph.adj[src], ans, trace, visited, graph);


        if (ans.size() == 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for (List<LineInfo> lineInfos : ans) {
            int tmp = 0;
            for (LineInfo lineInfo : lineInfos) {
                tmp += lineInfo.q;
            }
            min = Math.min(min, tmp);
        }
        return min;
    }

    private static void backtrace(int dest, int k, int src, LinkedList<LineInfo> edgeInfo, List<List<LineInfo>> ans, List<LineInfo> trace, boolean[][] visited, Graph graph) {
        // 当当前处理的节点是目标节点时
        if (src == dest) {
            if (trace.size() - 1 <= k) {
                ans.add(new ArrayList<>(trace));
            }
            return;
        }


        for (LineInfo lineInfo : edgeInfo) {
            // 如果当前的节点没有被访问过
            if (!visited[src][lineInfo.getV()]) {
                visited[src][lineInfo.getV()] = true;
                trace.add(lineInfo);
                backtrace(dest, k, lineInfo.getV(), graph.adj[lineInfo.getV()], ans, trace, visited, graph);
                trace.remove(trace.size() - 1);
                visited[src][lineInfo.getV()] = false;
            }
        }
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
    }
}
