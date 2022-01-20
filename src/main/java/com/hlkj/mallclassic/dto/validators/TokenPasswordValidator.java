package com.hlkj.mallclassic.dto.validators;


import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: mall-classic
 * @description: TokenPassword校验器
 * @author: 李向平
 * @create: 2021-03-26 11:35
 */
public class TokenPasswordValidator implements ConstraintValidator<TokenPassword,String> {

    private Integer min;
    private Integer max;

    @Override
    public void initialize(TokenPassword constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //校验逻辑
        if(StringUtils.isEmpty(s)){
            return true;//为了支持小程序不传递password的情况
        }
        return s.length() >= this.min && s.length() <= this.max;
    }
}
