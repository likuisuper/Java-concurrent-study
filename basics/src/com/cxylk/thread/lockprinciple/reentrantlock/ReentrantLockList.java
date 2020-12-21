package com.cxylk.thread.lockprinciple.reentrantlock;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname ReentrantLockList
 * @Description 使用ReentrantLock实现一个简单的线程安全的list
 * @Author likui
 * @Date 2020/12/20 20:47
 **/
public class ReentrantLockList {
    //线程不安全的list
    private ArrayList<String> arrayList = new ArrayList<>();
    //独占锁
    private volatile ReentrantLock lock = new ReentrantLock();

    //添加元素
    public void add(String e) {
        lock.lock();
        try {
            arrayList.add(e);
        } finally {
            lock.unlock();
        }
    }

    //删除元素
    public void remove(String e) {
        lock.lock();
        try {
            arrayList.remove(e);
        } finally {
            lock.unlock();
        }
    }

    //获取数据
    public String get(int index) {
        return arrayList.get(index);
    }

    public static void main(String[] args) {
        ReentrantLockList lockList = new ReentrantLockList();
        lockList.add("a");
        Thread thread1 = new Thread(() -> {
            lockList.add("b");
            lockList.remove("a");
            System.out.println("线程1:"+lockList.get(0));
        }, "线程1");

        Thread thread2 = new Thread(() -> {
            lockList.add("c");
            lockList.remove("a");
            System.out.println("线程2:"+lockList.get(0));
        }, "线程2");

        thread1.start();
        thread2.start();

    }
}
