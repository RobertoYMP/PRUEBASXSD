package com.murex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MXEventXSD {

    public static void main(String[] args) {
        SpringApplication.run(MXEventXSD.class, args);
        synchronized (MXEventXSD.class) {
            try {
                MXEventXSD.class.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}