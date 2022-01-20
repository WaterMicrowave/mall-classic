package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.annotations.ScopeLevel;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api
 * @ClassName: CategoryController
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:41
 * @Version: 1.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有顶级分类名称
     * @return
     */
    @RequestMapping("/titles")
    public UnifyResponse getAllCategoryTitles(){
        return categoryService.getAllCategoryTitles();
    }

    /**
     * 获取分类详情
     * @param cId
     * @return
     */
    @RequestMapping("/detail/{cId}")
    public UnifyResponse getDetail(@PathVariable String cId){
        if(StringUtils.isEmpty(cId)){
            throw new APIParamException();
        }
        return categoryService.getDetail(cId);
    }

    /**
     * 获取每个分类前4条
     * @param cId 二级分类id
     * @return
     */
//    @RequestMapping("/{cId}/spu/list")
//    public UnifyResponse getCategorySpuTop4List(@PathVariable String cId){
//        if(StringUtils.isEmpty(cId)){
//            throw new APIParamException();
//        }
//        return categoryService.getCategorySpuTop4List(cId);
//    }

    @RequestMapping("/{cId}/spu/list")
    public UnifyResponse getCategorySpuTop4List(@PathVariable String cId){
        if(StringUtils.isEmpty(cId)){
            throw new APIParamException();
        }
        return categoryService.getSpecialCategorySpuList(cId);
    }

}
