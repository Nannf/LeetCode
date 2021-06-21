package thread;

/**
 * @author Nannf
 * @date 2021/6/21 10:30
 * @description 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 * <p>
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * <p>
 * 输入: [1,2,3]
 * 输出: "firstsecondthird"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 正确的输出是 "firstsecondthird"。
 * <p>
 * 输入: [1,3,2]
 * 输出: "firstsecondthird"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
 * 正确的输出是 "firstsecondthird"。
 * <p>
 * 就是无论我怎么调用，方法二的执行必须在方法一之后，可以通过等待-通知
 */
public class Solution_1114 {
    class Foo {
        public Foo() {
        }

        volatile boolean firstDeal = false;
        volatile boolean secondDeal = false;

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (Foo.class) {
                printFirst.run();
                firstDeal = true;
                Foo.class.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (Foo.class) {
                while (!firstDeal) {
                    Foo.class.wait();
                }
                    printSecond.run();
                    secondDeal = true;
                    Foo.class.notifyAll();

            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (Foo.class) {
                while (!secondDeal) {
                    Foo.class.wait();
                }
                printThird.run();


            }
        }
    }
}
