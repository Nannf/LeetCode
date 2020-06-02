/**
 * @author Nannf
 * @date 2020/6/2 11:07
 * @description 循环队列-数组实现
 */
public class CircularQueue {
    private int queueSize;
    private Object[] data;
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int queueSize) {
        if (queueSize <= 0) {
            throw new IllegalArgumentException("队列的大小必须是正整数!");
        }
        this.queueSize = queueSize;
        data = new Object[queueSize];
    }

    // 入栈
    public boolean enqueue(Object obj) {
        // 队列满了
        if ((tail + 1) % queueSize == head) {
            return false;
        }
        data[tail] = obj;
        // 不能++了
//        tail++;
        tail = (tail + 1) % queueSize;
        return true;
    }

    public Object dequeue() {
        if (tail == head) {
            return null;
        }
        Object obj = data[head];
        head = (head + 1) % queueSize;
        return obj;
    }
}
