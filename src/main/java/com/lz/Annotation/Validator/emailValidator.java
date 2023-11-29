package com.lz.Annotation.Validator;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/08/8:11
 * @Description:
 */

import com.lz.Annotation.emailVerification;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 电子邮件验证器
 *
 * @author lz
 * @date 2023/11/08
 */
public class emailValidator implements ConstraintValidator<emailVerification, String> {

    private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final String USERNAME_PATTERN = "^(\\w{3,7})$";
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        
        
        // null 不做检验
        if (s == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher emailMatcher = pattern.matcher(s);
        if (!emailMatcher.matches()) {
            return Pattern.compile(USERNAME_PATTERN).matcher(s).matches();
        }
        // 校验成功
        return true;
    }
}