package com.lz.Annotation.Validator;

import com.lz.Annotation.HaveNoBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 没有空白验证器
 *
 * @author lz
 * @date 2023/11/06
 */
public class HaveNoBlankValidator implements ConstraintValidator<HaveNoBlank, String> {

    @Override

    public boolean isValid(String value, ConstraintValidatorContext context) {

        // null 不做检验
        if (value == null) {

            return true;
        }
        if (value.contains(" ")) {

            // 校验失败

            return false;

        }

        // 校验成功
        return true;

    }

}