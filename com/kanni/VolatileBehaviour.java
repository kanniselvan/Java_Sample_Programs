package com.kanni;

public class VolatileBehaviour {


   // private static long MY_INT = 0;
    private static volatile long MY_INT = 0;


    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }


    static class ChangeListener extends Thread {

        @Override
        public void run() {
            long local_value = MY_INT;
            while (local_value < 5) {
                if (local_value != MY_INT) {
                    System.out.println("Got Change for MY_INT : " + MY_INT);
                    local_value = MY_INT;
                }
            }

            System.out.println("ChangeListener thread Done" );
        }
    }

    static class ChangeMaker extends Thread {

        @Override
        public void run() {

            long local_value = MY_INT;
            while (MY_INT < 5) {
                System.out.println("Incrementing MY_INT to " + (local_value + 1));
                MY_INT = ++local_value;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ChangeMaker thread Done" );
        }
    }

}