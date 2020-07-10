import java.util.*;

/**
 * @auth Nannf
 * @date 2020/7/8 15:57
 * @description: 无向图
 * 邻接链表的方式
 */
public class Graph {
    // 顶点的个数
    private int v;

    // 邻接表
    private LinkedList<Integer>[] adj;

    private boolean found = false;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // 添加顶点s 和顶点t之间的连线
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.dfs(0, 7);
    }

    /**
     * 图的广度优先搜索
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        // 这个队列是用来保存每一层的所有节点
        Queue<Integer> queue = new ArrayDeque<>();
        // 这个是用来保存从s到t需要经过的路径
        List<Integer> path = new ArrayList<>();
        // 这个是用来记录哪些节点已经被访问过
        Set<Integer> visited = new HashSet<>();

        // 把开始节点放到队列中
        queue.offer(s);
        visited.add(s);

        while (!queue.isEmpty()) {
            // 保存本层所有节点的下一层节点
            List<Integer> tmpList = new ArrayList<>();
            while (!queue.isEmpty()) {
                int tmp = queue.poll();
                path.add(tmp);
                if (tmp == t) {
                    print(path);
                    return;
                }

                for (int i : adj[tmp]) {
                    // 如果这个节点已经被访问过，直接跳过
                    if (visited.contains(i)) {
                        continue;
                    }
                    visited.add(i);
                    tmpList.add(i);
                }

            }

            for (int i : tmpList) {
                queue.offer(i);
            }
        }
    }

    public void dfs(int s, int t) {
        if (s == t) {
            return;
        }
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        recuDfs(s,t,visited,path);
    }

    private void recuDfs(int s, int t, Set<Integer> visited, List<Integer> path) {
        if (found == true) {
            return;
        }
        visited.add(s);
        path.add(s);
        if (s == t) {
            found = true;
            print(path);
            return;
        }
        for (int i = 0; i < adj[s].size();i++) {
            if (!visited.contains(adj[s].get(i))) {
                recuDfs(adj[s].get(i),t,visited,path);
            }
        }
    }

    public void print(List<Integer> path) {
        for (int i : path) {
            System.out.printf(i + " -> ");
        }
    }
}
