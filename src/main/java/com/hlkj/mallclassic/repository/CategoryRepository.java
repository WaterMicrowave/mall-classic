package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.repository
 * @ClassName: BannerRepository
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:41
 * @Version: 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(nativeQuery = true, value = "select * from category where is_root=1 and online=1")
    List<Category> findAllRootCategory();

    /**
     * 查询二级分类id
     * @param cId
     * @return
     */
    @Query(nativeQuery = true, value = "select id from category where parent_id=:cId and online=1")
    List<String> selectSubCategoryIds(@Param("cId") String cId);

}
