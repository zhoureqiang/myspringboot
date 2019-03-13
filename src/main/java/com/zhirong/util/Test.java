package com.zhirong.util;

import java.util.concurrent.Callable;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName Test
 * @Description 多线程测试
 * @Author zhourq
 * @Date 2019/3/8 9:28
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args){
        long t1 = System.currentTimeMillis();
        MyThread thread = new MyThread();
        Task myTask = new Task();
        thread.start();
        myTask.run();
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

    }

    //继承Thread实现线程
    static class MyThread extends Thread{
        @Override
        public void run() {
            for (int i=0;i<1000;i++){
                System.out.println(Thread.currentThread()+"-->"+"继承Thread实现线程");
            }
        }
    }

    //实现Runnable接口实现线程
    static class Task implements Runnable{

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            for (int i=0;i<1000;i++){
                System.out.println(Thread.currentThread()+"-->"+"实现Runnable接口实现线程");
            }
        }
    }

    //实现Callable接口实现线程
    class Call implements Callable{

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Object call() throws Exception {
            for (int i=0;i<1000;i++){
                System.out.println(Thread.currentThread()+"-->"+"继承Thread实现线程");
            }
            return null;
        }
    }
}
