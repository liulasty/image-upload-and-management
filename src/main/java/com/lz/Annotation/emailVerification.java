package com.lz.Annotation;

import com.lz.Annotation.Validator.HaveNoBlankValidator;
import com.lz.Annotation.Validator.emailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/08/8:10
 * @Description:
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {emailValidator.class})// 标明由哪个类执行校验逻辑
public @interface emailVerification {
    // 校验出错时默认返回的消息
    String message() default "请输入账号或者邮箱";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * 同一个元素上指定多个该注解时使用
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {

        NotBlank[] value();

    }
}