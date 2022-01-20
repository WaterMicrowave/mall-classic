package com.hlkj.mallclassic.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Banner;
import com.hlkj.mallclassic.repository.BannerRepository;
import com.hlkj.mallclassic.utils.UUIDUtils;
import com.hlkj.mallclassic.vo.BannerVO;
import com.hlkj.mallclassic.vo.PageVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.service
 * @ClassName: BannerService
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:43
 * @Version: 1.0
 */
@Service
public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    public Banner getDetail(String bid){
        Banner banner = bannerRepository.findById(bid).orElseThrow(NotFoundException::new);
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
//        BannerVO bannerVO = mapper.map(banner, BannerVO.class);
//        return bannerVO;
        return banner;
    }

    public UnifyResponse add(Banner banner){
        UnifyResponse unifyResponse = null;

        banner.setId(UUIDUtils.generateUUID());
        Banner saveB = bannerRepository.save(banner);

        unifyResponse = UnifyResponse.buildSuccess(saveB);
        return unifyResponse;
    }

    public UnifyResponse listPageSort(Integer pageNum, Integer pageSize) {

        UnifyResponse unifyResponse = null;

        if(null==pageNum || null==pageSize){
            throw new APIParamException();
        }
        //1、构建PageRequest对象
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
        //2、查询
        Page<Banner> page = bannerRepository.findAll(pageRequest);
        if(ListUtils.isEmpty(page.getContent())){
            throw new NotFoundException();
        }

        unifyResponse = UnifyResponse.buildSuccess(page);
        //返回vo列表给前端
        return unifyResponse;
    }

    public UnifyResponse bannerVOListByPage(Integer pageNum, Integer pageSize) throws InvocationTargetException, IllegalAccessException {

        UnifyResponse unifyResponse = this.listPageSort(pageNum, pageSize);
        if (unifyResponse.getCode()==100200){
            Page<Banner> page = (Page<Banner>) unifyResponse.getData();
            List<Banner> bannerList = page.getContent();

            ArrayList<BannerVO> resultList = new ArrayList<>();
//            for (Banner b :
//                    bannerList) {
//                if(resultList==null){
//                    resultList = new ArrayList<>();
//                }
//                BannerVO bannerVO = new BannerVO();
//                BeanUtils.copyProperties(bannerVO, b);
//                resultList.add(bannerVO);
//            }
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            bannerList.forEach(s->{
                BannerVO bannerVO = mapper.map(s, BannerVO.class);
                resultList.add(bannerVO);
            });
            //组装分页数据
            PageVO<BannerVO> pageVO = new PageVO<>();
            pageVO.setPageNum(pageNum);
            pageVO.setPageSize(pageSize);
            pageVO.setTotalCount(page.getNumber());
            pageVO.setTotalPages(page.getTotalPages());
            pageVO.setHasMore(page.hasNext());
            pageVO.settList(resultList);
            return UnifyResponse.buildSuccess(pageVO);
        }
        return UnifyResponse.buildFailed();
    }

}
