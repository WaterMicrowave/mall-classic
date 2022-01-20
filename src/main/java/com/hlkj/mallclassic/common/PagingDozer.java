package com.hlkj.mallclassic.common;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: spring_boot-jpa-rabc
 * @Package: com.hs.classic.spring_bootjparabc.vo
 * @ClassName: PagingDozer
 * @Author: Administrator
 * @Description: 泛型T：model  泛型K：vo
 * @Date: 2020/11/12 18:24
 * @Version: 1.0
 */
public class PagingDozer<T,K> extends Paging {

    public PagingDozer(Page<T> pageT, Class<K> classK){
        //1、调用基类初始化
        this.initPageParameters(pageT);
        List<T> tList = pageT.getContent();
        //2、使用dozemapper进行列表类型的属性拷贝
        List<K> voList = new ArrayList<>();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        tList.forEach(t -> {
            K vo = mapper.map(t,classK);
            voList.add(vo);
        });
        this.setItems(voList);
    }
}