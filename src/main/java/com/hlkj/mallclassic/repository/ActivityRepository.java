package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.repository
 * @ClassName: ActivityRepository
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:41
 * @Version: 1.0
 */
public interface ActivityRepository extends JpaRepository<Activity, String> {

    List<Activity> findAllByIdIn(List<String> ids);

}
