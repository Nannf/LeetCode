import java.util.Stack;

/**
 * @author Nannf
 * @date 2020/6/1 22:01
 * @description 使用栈实现队列的下列操作：
 * <p>
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 */
public class Solution_232 {
    class MyQueue2 {
        private Stack<Integer> data = new Stack<>();
        private int size = 10;
        private int elementCount = 0;

        /**
         * Initialize your data structure here.
         */
        public MyQueue2() {
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            if (elementCount == size) {
                throw new RuntimeException("队列已满");
            }
            data.add(elementCount, x);
            elementCount++;
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            int value = data.firstElement();
            data.remove(0);
            elementCount--;
            return value;

        }

        /**
         * Get the front element.
         */
        public int peek() {
            return data.firstElement();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            if (elementCount == 0) {
                return true;
            }
            return false;
        }
    }


    class MyQueue {
        // 两个栈,每次插入的时候,先把这个栈里的数据push到s2中,在push回来
        // 就把后进先出变成先进先出
        private Stack<Integer> s1 = new Stack<>();
        private Stack<Integer> s2 = new Stack<>();
        // 存放的队首的值
        private int font;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            // 如果s1 是空
            if (s1.isEmpty()) {
                // 队首的值,下次改变队首的值是出队的时候
                font =x;
                s1.push(x);
            } else {
                // 先把s1的值全push到s2中
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
                s1.push(x);
                while (!s2.isEmpty()) {
                    s1.push(s2.pop());
                }
            }

        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if(s1.isEmpty()) {
                return 0;
            }
            int value = s1.pop();
            if (s1.isEmpty()) {
                font = 0;
            } else {
                font = s1.peek();
            }
            return value;
        }

        /**
         * Get the front element.
         */
        public int peek() {
            return font;
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return s1.isEmpty();
        }
    }
}
