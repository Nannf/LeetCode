import java.util.*;

/**
 * @auth Nannf
 * @date 2020/8/4 10:48
 * @description: 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 *  输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_207 {

    public static void main(String[] args) {
        int num = 2;
        int[][] prerequisites = {{1,0},{0,1}};
        if (canFinish(num,prerequisites)) {
            System.out.println("bingo!");
        }
    }

    /**
     * 拓扑排序
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 假设有A，B,C三个课程，那我们用 A->C 表示A要在C之前进行选修，我们要选修那些入度为0的课程先选修，等选修完之后，把所有以该课程为前提的课程的入度-1
        // 我们判断是否可以上完，就判断当所有入度为0 的课程全部选修完之后，已经选修的数量和给定的数量是否一致就可以了

        // 学习的顺序
        List<Integer> studyOrder = new ArrayList<>();
        //list[i] 表示所有以list[i] 为前提的元素
        LinkedList<Integer>[] list = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            list[i] = new LinkedList<>();
        }
        // 第一步先计算每个课程的入度
        int[] rd = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            // 二维数组只包含两个数据，第一个是当前要修的课，第二个是它的前提课
            rd[prerequisites[i][0]] += 1;
            // 注意这边的顺序
            list[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        // 存放所有入度唯一的节点
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < rd.length; i++) {
            // 找到所有入度为0 的课程
            if (rd[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            // 把队首的元素获取出来
            int w = queue.remove();
            // 放到学习的列表里
            studyOrder.add(w);
            // 把所有以w为入度的元素的入度-1
            for (int i : list[w]) {
                rd[i] -= 1;
                if (rd[i] == 0) {
                    queue.add(i);
                }
            }
        }
        return studyOrder.size() == numCourses;
    }
}
