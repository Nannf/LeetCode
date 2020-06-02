/**
 * @author Nannf
 * @date 2020/6/1 21:10
 * @description 数组实现的队列
 */
public class ArrayQueue {
    private int queueSize;
    private Object[] data;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int queueSize) {
        if (queueSize <= 0) {
            throw new IllegalArgumentException("队列的大小必须是正整数!");
        }
        this.queueSize = queueSize;
        data = new Object[queueSize];
    }

    public boolean enqueue(Object obj) {
        if (tail == queueSize) {
            if (head == 0) {
                return false;
            }
            for (int i = head; i < tail; i++) {
                data[i-head] = data[i];
            }
            tail = tail -head;
            head = 0;
        }
        data[tail] = obj;
        tail++;
        return true;
    }

    public Object dequeue() {
        if (tail == head) {
            return null;
        }
        Object obj = data[head++];
        return obj;
    }
}
