package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Activity;
import com.hlkj.mallclassic.model.Banner;
import com.hlkj.mallclassic.repository.ActivityRepository;
import com.hlkj.mallclassic.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

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
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getListByIds(List<String> ids){
        List<Activity> list = activityRepository.findAllByIdIn(ids);
        if(ListUtils.isEmpty(list)){
            throw new NotFoundException();
        }
        return list;
    }

}
