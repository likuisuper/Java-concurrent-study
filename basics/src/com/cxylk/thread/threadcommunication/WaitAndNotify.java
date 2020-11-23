package com.cxylk.thread.threadcommunication;

/**
 * @Classname WaitAndNotify
 * @Description 等待/通知机制，基于Object的wait()方法和notify()、notifyAll()方法
 * @Author likui
 * @Date 2020/11/23 15:25
 **/
public class WaitAndNotify {
    private static Object lock=new Object();

    static class ThreadA implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadA:"+i);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    static class ThreadB implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadB:"+i);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }
}
