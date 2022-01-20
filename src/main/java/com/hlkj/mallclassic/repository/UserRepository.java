package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.repository
 * @ClassName: BannerRepository
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:41
 * @Version: 1.0
 */
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByNickNameAndPwd(String userName, String pwd);

    Optional<User> findFirstByOpenIdEquals(String openid);
}
