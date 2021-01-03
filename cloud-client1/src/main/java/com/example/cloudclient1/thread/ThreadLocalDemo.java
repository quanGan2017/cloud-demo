package com.example.cloudclient1.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                String data = new ThreadLocalDemo().date(finalI);
                System.out.println(data);
            }).start();
            Thread.sleep(100);
        }

    }

    private String date(int seconds){
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }
}
