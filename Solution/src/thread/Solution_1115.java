package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nannf
 * @date 2021/6/22 7:48
 * @description 我们提供一个类：
 * <p>
 * class FooBar {
 * public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 * }
 * <p>
 * public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 * }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 示例 2:
 * <p>
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 */
public class Solution_1115 {
    class FooBar {
        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        private final ReentrantLock lock = new ReentrantLock();
        volatile boolean alreadyFoo = false;
        volatile boolean alreadyBar = true;
        Condition waitBarCondition = lock.newCondition();
        Condition waitFooCondition = lock.newCondition();


        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                try {
                    lock.lock();
                    while (!alreadyBar) {
                        waitFooCondition.await();
                    }
                    printFoo.run();
                    alreadyFoo = true;
                    alreadyBar = false;
                    waitBarCondition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                try {
                    lock.lock();
                    while (!alreadyFoo) {
                        waitBarCondition.await();
                    }
                    printBar.run();
                    alreadyBar = true;
                    alreadyFoo = false;
                    waitFooCondition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
