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
    class MyQueue {
        private Stack<Integer> data = new Stack<>();
        private int size = 0;
        private int elementCount = 0;

        /**
         * Initialize your data structure here.
         */
        public MyQueue(int size) {
            this.size = size;
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
}
