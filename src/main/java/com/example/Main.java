package com.example;

import com.example.string.builder.NewStringBuilder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        NewStringBuilder nsb = new NewStringBuilder();

        nsb.append("Hello, ");
        nsb.append("World!");

        System.out.println(nsb);
        System.out.println("Длина: " + nsb.length());

        nsb.undo();

        nsb.append("Java");
        System.out.println(nsb);
        System.out.println("Длина: " + nsb.length());

        nsb.undo();
        nsb.undo();

        System.out.println(nsb);
        System.out.println("Длина: " + nsb.length());

        nsb.append(2026);
        nsb.append(" год");

        System.out.println(nsb);
        System.out.println("Длина: " + nsb.length());
    }
}