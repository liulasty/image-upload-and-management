package com.lz.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/16:49
 * @Description:
 */

import com.lz.pojo.dto.AthleteDTO;
import com.lz.pojo.result.Result;
import com.lz.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lz
 */
@RestController
@RequestMapping("sports/athlete")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AthleteController {
    
    @Autowired
    private AthleteService athleteService;
    
    @PostMapping
    public Result add(@RequestBody AthleteDTO athleteDTO){
        
        athleteService.add(athleteDTO);
        
        return Result.success("申请成功");
    }
}