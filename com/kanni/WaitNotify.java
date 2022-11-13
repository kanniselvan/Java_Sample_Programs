package com.kanni;

public class WaitNotify {

    private static final Object lock = new Object();
    private static volatile boolean isTick = true;


    public static void main(String[] args) {
        int concurrency = 5;

        for (int i = 0; i < concurrency; i++) {
            new Tick(i).start();
        }

        for (int i = 0; i < concurrency; i++) {
            new Tock(i).start();
        }
    }


    private static class Tick extends Thread {

        private final int number;


        Tick(int number) {
            this.number = number;
        }


        @Override
        public void run() {
            synchronized (lock) { //use monitor of lock
                try {
                    while (!isTick) {
                        lock.wait(); //wait for our turn
                    }
                    System.out.println(String.format("Tick [%03d] ", number));
                    isTick = false;
                    lock.notifyAll(); //tell about that we made our job
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Tock extends Thread {

        private final int number;


        Tock(int number) {
            this.number = number;
        }


        @Override
        public void run() {
            synchronized (lock) {
                try {
                    while (isTick) {
                        lock.wait();
                    }
                    System.out.printf(" Tock [%03d] \n", number);
                    isTick = true;
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}