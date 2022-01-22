package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Banner;
import com.hlkj.mallclassic.model.Collect;
import com.hlkj.mallclassic.repository.CollectRepository;
import com.hlkj.mallclassic.utils.TimeUtils;
import com.hlkj.mallclassic.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

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
public class CollectService {

    @Autowired
    private CollectRepository collectRepository;

    public UnifyResponse add(Collect collect){
        UnifyResponse unifyResponse = null;

        collect.setId(UUIDUtils.generateUUID());
        collect.setCreateTime(TimeUtils.getCurrTimeStamp());
        Collect saveC = collectRepository.save(collect);

        unifyResponse = UnifyResponse.buildSuccess(saveC);
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
        Page<Collect> page = collectRepository.findAll(pageRequest);
        if(ListUtils.isEmpty(page.getContent())){
            throw new NotFoundException();
        }

        unifyResponse = UnifyResponse.buildSuccess(page);
        //返回vo列表给前端
        return unifyResponse;
    }

}
