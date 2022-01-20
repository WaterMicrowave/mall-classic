package com.hlkj.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-04-06 15:31
 */
public class MathTest {

    public static void main(String[] args) {
        ArrayList<Teacher> list = new ArrayList<>();
        list.add(new Teacher("ls",25));
        list.add(new Teacher("zs",20));
        Collections.sort(list, new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        System.out.println(list);
    }
}

class Teacher {
    private String name;
    private Integer age;

    public Teacher(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
