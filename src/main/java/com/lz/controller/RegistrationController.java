package com.lz.controller;


import com.lz.pojo.result.PageResult;
import com.lz.pojo.result.Result;
import com.lz.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lz
 */
@Slf4j
@RestController
@RequestMapping("sports/registration")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    /**
     * 分页查询列表
     *
     * @param name        名字
     * @param status      地位
     * @param date        日期
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     *
     * @return {@code Result<PageResult>}
     */
    @GetMapping("/page")
    public Result<PageResult> list(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String status,
                                   @RequestParam(required = false) String date,
                                   @RequestParam(defaultValue = "1") int currentPage,
                                   @RequestParam(defaultValue = "5") int pageSize) {

        System.out.println("date:" + date);
        PageResult registrationList = registrationService.list(currentPage,
                                                               pageSize, name,
                                                               status,
                                                               stringToData(date));

        return Result.success(registrationList);
    }

    /**
     * 删除
     *
     * @param id 编号
     *
     * @return {@code Result<String>}
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id){
        registrationService.delete(id);
        return Result.success("删除成功");
    }
    
    @PutMapping("/{id}")
    public Result<String> attend(@PathVariable Long id){
        registrationService.attend(id);
        
        return Result.success("同意参加");
    }

    public Date stringToData(String s){

        if(s == null || "".equals(s)){
            return null;
        }
        Date date;
        try {
            String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            date = formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return date;
    }
}