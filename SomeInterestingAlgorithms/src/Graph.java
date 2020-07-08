import java.util.LinkedList;

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

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // 添加顶点s 和顶点t之间的连线
    public void addEdge(int s,int t) {
        adj[s].add(t);
        adj[t].add(s);
    }
}
