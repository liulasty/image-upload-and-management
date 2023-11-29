package com.lz.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/19:48
 * @Description:
 */

import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.entity.Project;
import com.lz.pojo.result.PageResult;
import com.lz.pojo.result.Result;
import com.lz.pojo.vo.ProjectVO;
import com.lz.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lz
 */
@RestController
@RequestMapping("sports/project")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    
    @GetMapping("/page")
    public Result<PageResult> list(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String type,
                                   @RequestParam(required = false) String date,
                                   @RequestParam(defaultValue = "1") int currentPage,
                                   @RequestParam(defaultValue = "5") int pageSize) {
        
        
        int offset = (currentPage - 1) * pageSize;
        EventListDto listDto = new EventListDto(name, type, date, offset, pageSize);

        PageResult list = projectService.list(listDto);

        return Result.success(list);
    }
    
    
}