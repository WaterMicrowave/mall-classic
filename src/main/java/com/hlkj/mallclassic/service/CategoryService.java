package com.hlkj.mallclassic.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Category;
import com.hlkj.mallclassic.model.Spu;
import com.hlkj.mallclassic.repository.CategoryRepository;
import com.hlkj.mallclassic.repository.SpuRepository;
import com.hlkj.mallclassic.vo.CategorySimpleVO;
import com.hlkj.mallclassic.vo.CategorySpuVO;
import com.hlkj.mallclassic.vo.CategoryVO;
import com.hlkj.mallclassic.vo.SpuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.service
 * @ClassName: CategoryService
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:20
 * @Version: 1.0
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SpuRepository spuRepository;

    /**
     * 获取所有分类名称
     * @return
     */
    public UnifyResponse getAllCategoryTitles(){
        List<Category> list = categoryRepository.findAllRootCategory();
        ArrayList<CategorySimpleVO> resultList = new ArrayList<>();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        list.forEach(s->{
            CategorySimpleVO bannerVO = mapper.map(s, CategorySimpleVO.class);
            resultList.add(bannerVO);
        });

        return UnifyResponse.buildSuccess(resultList);
    }

    /**
     * 获取分类详情
     * @param cId
     * @return
     */
    public UnifyResponse getDetail(String cId){
        Category category = categoryRepository.findById(cId).orElseThrow(APIParamException::new);
        return UnifyResponse.buildSuccess(category);
    }

    /**
     * 根据分类id获取分类及当前分类下4个商品
     * @param cId
     * @return
     */
    public UnifyResponse getCategorySpuTop4List(String cId){
        //1、根据分类id获取分类信息
        Category category = categoryRepository.findById(cId).get();
        if (null == category){
            throw new NotFoundException();
        }
        //2、进行属性拷贝
        List<SpuVO> spuVOList = new ArrayList<>();
        //3、获取当前分类下的4商品
        List<Spu> spuList = spuRepository.findByCategoryIdTop4(cId);

        if (!ListUtils.isEmpty(spuList)){
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            spuList.forEach(s->{
                SpuVO spuVO = mapper.map(s, SpuVO.class);
                spuVOList.add(spuVO);
            });
        }
        //3、CategoryVO属性拷贝
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category, categoryVO);
        //4、组装CategorySpuVO
        CategorySpuVO categorySpuVO = new CategorySpuVO();
        categorySpuVO.setCategoryVO(categoryVO);
        categorySpuVO.setSpuVOList(spuVOList);

        return UnifyResponse.buildSuccess(categorySpuVO);
    }

    /**
     * 获取指定一级分类下所有二级分类前4记录
     * @param cId 一级分类
     * @return
     */
    public UnifyResponse getSpecialCategorySpuList(String cId){
        List<String> subCIds = categoryRepository.selectSubCategoryIds(cId);
        if(ListUtils.isEmpty(subCIds)){
            throw new NotFoundException();
        }
        List<CategorySpuVO> resultList = new ArrayList<>();
        for (String cid:
             subCIds) {
            UnifyResponse unifyResponse = getCategorySpuTop4List(cid);
            if(unifyResponse.getCode()==100200){
                CategorySpuVO categorySpuVO = (CategorySpuVO) unifyResponse.getData();
                resultList.add(categorySpuVO);
            }
        }
        return UnifyResponse.buildSuccess(resultList);
    }

}
