package com.hlkj.mallclassic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallClassicApplicationTests {

    @Test
    void contextLoads() {
        int create = 1;//create 新增权限
        int delete = 2;//del 删除权限
        int update = 4;//update 更新权限
        int select = 8;//select 详情查看权限
        int list = 16;//list 列表查看权限（一般只要有“菜单权限”就会有列表查看权限）

        int listAndCreateAndDelete = list | create | delete;
        int listAndSelect = list | select;
        System.out.println("列表、新增、删除 权限值：" + listAndCreateAndDelete);
        System.out.println("对应的二进制："+Integer.toBinaryString(listAndCreateAndDelete));

        System.out.println("列表、详情 权限值：" + listAndSelect);
        System.out.println("对应的二进制：" + Integer.toBinaryString(listAndSelect));
    }

}
