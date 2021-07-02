package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author Nannf
 * @date 2021/6/23 17:43
 * @description 假设有这么一个类：
 * <p>
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 * public void zero(printNumber) { ... }  // 仅打印出 0
 * public void even(printNumber) { ... }  // 仅打印出 偶数
 * public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * <p>
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 * <p>
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * <p>
 * 输入：n = 5
 * 输出："0102030405"
 * <p>
 * 问题就在找到没个方法执行的等待条件
 * - 最开始的0是没有任何条件的，之后的每个0的打印都需要even 或者 odd 任何一个方法的调用
 * - odd 最开始是需要 0 调用之后才能调用，后面所有的odd 都需要 0 和 even调用
 * - even 最开始需要 0 和 odd 0 然后才是0 odd 0
 */
public class Solution_1116 {

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(4);
        IntConsumer intConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        };
        Thread t1 = new Thread(() -> {
                try {
                    zeroEvenOdd.zero(intConsumer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });


        Thread t2 = new Thread(() -> {
                try {
                    zeroEvenOdd.odd(intConsumer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });

        Thread t3 = new Thread(() -> {
                try {
                    zeroEvenOdd.even(intConsumer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });
        t1.start();
        t2.start();
        t3.start();

    }

    static class ZeroEvenOdd {
        private int n;

        // 这个是用来结束循环的
        AtomicInteger count;
        // 这个是用来打印的，只有打印0的时候，才会增加
        AtomicInteger printNum = new AtomicInteger(0);

        ReentrantLock lock = new ReentrantLock();

        volatile boolean first = true;

        volatile  boolean isZero = false;
        Condition zeroCondition = lock.newCondition();
        Condition oddCondition = lock.newCondition();
        Condition evenCondition = lock.newCondition();

        public ZeroEvenOdd(int n) {
            this.n = n;
            count = new AtomicInteger(n * 2);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                try {
                    lock.lock();
                    // 如果是刚开始执行，只有zero可以运行
                    if (first) {
                        // 先打印一个0
                        printNumber.accept(0);
                        // 总数减一
                        count.getAndDecrement();
                        // 打印的值加一
                        printNum.getAndIncrement();
                        // 把第一次的去掉
                        first = false;
                        isZero = true;
                        System.out.println("我是0，我释放了奇数的锁");
                        oddCondition.signalAll();
                    } else {
                        while (isZero) {
                            System.out.println("我是0，我把自己锁了");
                            zeroCondition.await();
                        }
                        // 只打印0
                        printNumber.accept(0);
                        isZero = true;

                        // count的值减一
                        count.decrementAndGet();
                        if (count.get() <= 0) {
                            return;
                        }

                        if (printNum.get() % 2 != 0) {
                            System.out.println("我是0，我释放了奇数的锁");
                            oddCondition.signalAll();
                        } else {
                            System.out.println("我是0，我释放了偶数的锁");
                            evenCondition.signalAll();
                        }

                    }
                } finally {
                    lock.unlock();
                }
            }

        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                try {
                    lock.lock();
                    while (first) {
                        System.out.println("我是偶数，我因为first锁了自己");
                        evenCondition.await();
                    }
                    // 这地方必须加 isZero，不然如果这个地方调度的比较慢的话，可能会直接通过，造成 012这样的输出序列
                    while (printNum.get() % 2 != 0 || !isZero) {
                        System.out.println("我是偶数，我因为printNum锁了自己");
                        evenCondition.await();
                    }

                    printNumber.accept(printNum.getAndIncrement());
                    count.decrementAndGet();
                    if (count.get() <= 0) {
                        return;
                    }
                    System.out.println("我是偶数，我释放了0");
                    isZero = false;
                    zeroCondition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                try {
                    lock.lock();
                    while (first) {
                        System.out.println("我是奇数，我因为first锁了自己");
                        oddCondition.await();
                    }
                    while (printNum.get() % 2 == 0 || !isZero) {
                        System.out.println("我是奇数，我因为printNum锁了自己");
                        oddCondition.await();
                    }

                    printNumber.accept(printNum.getAndIncrement());
                    count.decrementAndGet();
                    if (count.get() <= 0) {
                        return;
                    }
                    System.out.println("我是奇数，我释放了0");
                    isZero=false;
                    zeroCondition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
