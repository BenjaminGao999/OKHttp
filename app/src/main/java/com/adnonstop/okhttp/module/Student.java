package com.adnonstop.okhttp.module;

/**
 * Author:　Created by benjamin
 * DATE :  2017/2/8 19:39
 */

public class Student implements Person {
    public String name;
    public int num;

    public Student(String name, int num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
