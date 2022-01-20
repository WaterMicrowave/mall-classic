package com.hlkj.mallclassic.vo;

import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.vo
 * @ClassName: CategorySpuVO
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:23
 * @Version: 1.0
 */
public class CategorySpuVO {
    private CategoryVO categoryVO;
    private List<SpuVO> spuVOList;

    @Override
    public String toString() {
        return "CategorySpuVO{" +
                "categoryVO=" + categoryVO +
                ", spuVOList=" + spuVOList +
                '}';
    }

    public CategoryVO getCategoryVO() {
        return categoryVO;
    }

    public void setCategoryVO(CategoryVO categoryVO) {
        this.categoryVO = categoryVO;
    }

    public List<SpuVO> getSpuVOList() {
        return spuVOList;
    }

    public void setSpuVOList(List<SpuVO> spuVOList) {
        this.spuVOList = spuVOList;
    }
}
