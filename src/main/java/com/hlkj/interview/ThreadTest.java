package com.hlkj.interview;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-04-06 13:31
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.run();
    }

}

class Thread1 extends  Thread{
    @Override
    public void run() {
        super.run();
        for (int i=0;i<100;i++){
            System.out.println(i);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
