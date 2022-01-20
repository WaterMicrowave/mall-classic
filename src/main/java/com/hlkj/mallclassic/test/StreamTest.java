package com.hlkj.mallclassic.test;

import com.hlkj.mallclassic.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: mall-classic
 * @description: 测试jdk8新特性
 * @author: 李向平
 * @create: 2021-03-25 18:04
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("李向平1");
        list1.add("李向平2");
        list1.add("李向平3");
        list1.add("小李");

        List<String> result = list1.stream()
                .filter(l -> l.startsWith("李"))//筛选
                .map(String::new)
                .collect(Collectors.toList());
        System.out.println(result);

        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        Integer integer = nums.stream()
                .mapToInt((num)->num)
                .sum();
        System.out.println(integer);

        Optional<String> 天道酬勤 = Optional.of("天道酬勤");
        String s = 天道酬勤.orElseThrow(NotFoundException::new);
        System.out.println(s);

        Optional<Integer> optional = Optional.ofNullable(null);
        System.out.println(optional.orElse(200));
        System.out.println(optional.orElseGet(()->100));
    }
}
