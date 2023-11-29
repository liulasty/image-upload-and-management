package com.lz.exceptionHandling;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/06/8:29
 * @Description:
 */

import com.lz.pojo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lz
 * 
 * 通过全局异常处理的方式统一处理异常。
 */
@RestControllerAdvice
@RestController
@Slf4j
public class GlobalControllerAdvice {
    private static final String BAD_REQUEST_MSG = "客户端请求参数错误";
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);


    // <1> 处理 form data方式调用接口校验失败抛出的异常

    @ExceptionHandler(BindException.class)

    public Result bindExceptionHandler(BindException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<String> collect = fieldErrors.stream()

                .map(o -> o.getDefaultMessage())

                .collect(Collectors.toList());

        return Result.error(collect.toString());

    }

    // <2> 处理 json 请求体调用接口校验失败抛出的异常

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<String> collect = fieldErrors.stream()

                .map(o -> o.getDefaultMessage())

                .collect(Collectors.toList());

        return Result.error(collect.toString());

    }

    // <3> 处理单个参数校验失败抛出的异常

    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        List<String> collect = constraintViolations.stream()

                .map(o -> o.getMessage())

                .collect(Collectors.toList());

        return Result.error(collect.toString());

    }
    
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        System.out.println("SQLIntegrityConstraintViolationException = "+e);
                

        return Result.error("SQLIntegrityConstraintViolationException");
    }

    /**
     * 异常处理程序
     * 处理SQL异常
     *
     * @param e e
     *
     * @return {@code Result}
     */
    @ExceptionHandler(value = SQLException.class)
    public Result exceptionHandler(SQLException e)  {
       
        logger.error("发生SQL异常！原因是:",e);
        return Result.error(e.getMessage());
    }
    
    @ExceptionHandler(value = NullPointerException.class)
    public Result exceptionHandler(NullPointerException e){
        return Result.error(e.getMessage());
    }
}