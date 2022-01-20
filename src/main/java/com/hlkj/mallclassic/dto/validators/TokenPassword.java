package com.hlkj.mallclassic.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD}) //使用位置（类，方法，属性）
@Retention(RetentionPolicy.RUNTIME) //加载到jvm里运行
@Constraint(validatedBy = TokenPasswordValidator.class)
public @interface TokenPassword {
    String message() default "字段不符合要求";
    int min() default 6;
    int max() default 12;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
