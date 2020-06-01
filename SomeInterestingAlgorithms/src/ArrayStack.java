/**
 * @author Nannf
 * @date 2020/6/1 11:48
 * @description 数组实现的栈
 * 后进先出,先进后出,只有入栈和出栈两种操作
 */
public class ArrayStack {
    // 栈大小
    private int stackSize;
    // 数据
    private Object[] data;
    // 栈中数据元素大小
    private int count = 0;

    public ArrayStack(int stackSize) {
        this.stackSize = stackSize;
        data = new Object[stackSize];
    }

    /**
     * 出栈
     *
     * @return
     */
    public Object pop() {
        if (count == 0) {
            return null;
        }
        Object obj = data[count - 1];
        count--;
        return obj;
    }

    public boolean push(Object obj) {
        if (count == stackSize) {
            System.out.println("栈空间不足!");
            return false;
        }
        data[count] = obj;
        count++;
        return true;
    }


}
