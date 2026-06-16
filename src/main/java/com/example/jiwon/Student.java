package com.example.jiwon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Column
    private String name;
    @Id
    @Column
    private Integer number;
    @Column
    private Integer age;

    public Student() {
    }

    public Student(String name, Integer number, Integer age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }

    public void show() {
        System.out.println("이름 : "+name);
        System.out.println("학번 : "+number);
        System.out.println("나이 : "+age);
    }

    public static void main(String[] args) {
        Student s = new Student("윤지원", 1310, 17);
        s.show();
    }

}
