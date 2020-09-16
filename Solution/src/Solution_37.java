
import java.util.*;

/**
 * @auth Nannf
 * @date 2020/9/15 17:39
 * @description: 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class Solution_37 {

    public static void main(String[] args) {
        char[][] board = {
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'}
        };
        new Solution_37().solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(new String(chars));
        }
    }

    // 题目的意思是给定的9*9
    private static final int SIZE = 9;

    boolean findFlag = false;

    private static final Map<Integer, List<Point>> NINE_INFO = new HashMap<>();

    static {
        int count = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                List<Point> points = new ArrayList<>();
                int iStart = i * 3;
                int iEnd = i * 3 + 2;
                int jStart = j * 3;
                int jEnd = j * 3 + 2;

                for (int m = iStart; m <= iEnd; m++) {
                    for (int n = jStart; n <= jEnd; n++) {
                        Point point = new Point(m, n);
                        points.add(point);
                    }
                }
                NINE_INFO.put(count++, points);
            }

        }
    }

    public void solveSudoku(char[][] board) {
        PlaceMap map = new PlaceMap();
        backtrace(map, board, 0, 0);
        backfill(map, board);

    }

    private void backfill(PlaceMap map, char[][] board) {
        for (Map.Entry<Point, Integer> entry : map.placeInfo.entrySet()) {
            Point point = entry.getKey();
            board[point.x][point.y] = String.valueOf(entry.getValue()).charAt(0);
        }
    }

    private void backtrace(PlaceMap map, char[][] board, int x, int y) {
        // 如果超过了处理的边界，直接退出
        if (x >= SIZE || findFlag) {
            findFlag = true;
            return;
        }

        // 从每一个格子开始找起
        for (; y < SIZE; y++) {
            // 如果是数字，就会一直顺延
            while (Character.isDigit(board[x][y]) || map.containsKey(x, y)) {
                y++;
                if (y == SIZE) {
                    y = 0;
                    x++;
                    if (x >= SIZE) {
                        findFlag = true;
                        return;
                    }
                }
            }
            boolean flag = false;
            // 直到找到第一个不是数字的位置
            // 这是所有的可能数值
            for (int m = 1; m <= SIZE; m++) {
                // 判断是否合适
                if (isLegal(m, board, x, y, map)) {
                    flag = true;
                    int x1 = x;
                    int y1 = y;
                    // 如果合适的话 就添加上去
                    map.add(x, y, m);
                    // 添加上去之后 就找下一个格子
                    y++;
                    if (y == SIZE) {
                        y = 0;
                        x++;
                        if (x >= SIZE) {
                            findFlag = true;
                            return;
                        }
                    }
                    // 拿下一级的作为参数进行回溯
                    backtrace(map, board, x, y);

                    if (findFlag) {
                        return;
                    }
                    // 回溯失败之后 需要把本次做的决定撤销
                    map.remove(x1, y1);
                    flag =false;
                    y = y1;
                    x = x1;
                }
            }
            if (!flag) {
                return;
            }

        }


    }

    private boolean isLegal(int m, char[][] board, int i, int j, PlaceMap map) {

        // 先判断同行有没有
        for (int k = 0; k < SIZE; k++) {
            if (Character.isDigit(board[i][k])) {
                if (m == Integer.parseInt(String.valueOf(board[i][k]))) {
                    return false;
                }
            }
            if (map.containsKey(i, k) && map.get(i, k) == m) {
                return false;
            }
        }

        // 再判断同列有没有
        for (int k = 0; k < SIZE; k++) {
            if (Character.isDigit(board[k][j])) {
                if (m == Integer.parseInt(String.valueOf(board[k][j]))) {
                    return false;
                }
            }
            if (map.containsKey(k, j) && map.get(k, j) == m) {
                return false;
            }
        }

        // 再判断九宫格有没有
        // 先拿九宫格的key
        int nineKey = (i / 3) * 3 + j / 3;
        List<Point> points = NINE_INFO.get(nineKey);

        for (Point point : points) {
            if (Character.isDigit(board[point.x][point.y])) {
                if (m == Integer.parseInt(String.valueOf(board[point.x][point.y]))) {
                    return false;
                }
            }

            if (map.containsKey(point.x, point.y) && map.get(point.x, point.y) == m) {
                return false;
            }
        }
        return true;
    }


    public class PlaceMap {
        private Map<Point, Integer> placeInfo = new HashMap<>();


        public boolean containsKey(int x, int y) {
            for (Map.Entry<Point, Integer> entry : placeInfo.entrySet()) {
                if (entry.getKey().x == x && entry.getKey().y == y) {
                    return true;
                }
            }
            return false;
        }

        public void add(int x, int y, int value) {
            if (containsKey(x, y)) {
                remove(x, y);
            }
            Point point = new Point(x, y);
            placeInfo.put(point, value);
        }

        public void remove(int x, int y) {
            for (Map.Entry<Point, Integer> entry : placeInfo.entrySet()) {
                if (entry.getKey().x == x && entry.getKey().y == y) {
                    placeInfo.remove(entry.getKey());
                    return;
                }
            }
        }

        public int get(int x, int y) {
            for (Map.Entry<Point, Integer> entry : placeInfo.entrySet()) {
                if (entry.getKey().x == x && entry.getKey().y == y) {
                    return entry.getValue();
                }
            }
            System.out.println("非法");
            return -1;
        }

    }

    public static class Point {
        private int x;
        private int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
