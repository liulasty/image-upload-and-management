package com.lz.controller;



import com.lz.pojo.dto.RegistrationDTO;
import com.lz.pojo.entity.Registration;
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
                                                               StringToData(date));

        return Result.success(registrationList);
    }

    public Date StringToData(String s){

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