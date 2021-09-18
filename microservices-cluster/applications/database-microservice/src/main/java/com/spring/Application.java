package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//
//        ProcessBuilder p = new ProcessBuilder();
//        System.out.println("Starting EXE");
//        p.command("./../../services/ngrok/ngrok.exe","http","80");
//
//        try {
//            p.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("EXE Started");

    }

}
