package com.lz.controller;



import com.lz.Exception.MyException;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.dto.JoinProjectDTO;
import com.lz.pojo.dto.ProjectDTO;
import com.lz.pojo.entity.Project;
import com.lz.pojo.result.PageResult;
import com.lz.pojo.result.Result;
import com.lz.pojo.vo.ProjectVO;
import com.lz.service.ImgService;
import com.lz.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @author lz
 */
@Slf4j
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

        log.info("分页查询:{},{}", currentPage,pageSize);
        int offset = (currentPage - 1) * pageSize;
        EventListDto listDto = new EventListDto(name, type, date, offset, pageSize);
        
        PageResult list = projectService.list(listDto);

        return Result.success(list);
    }

    @PostMapping()
    public Result<String> addProject(@RequestBody ProjectDTO projectDTO){
        
        projectService.add(projectDTO);

        return Result.success("添加项目成功");
    }
    
    @GetMapping("/{id}")
    public Result<ProjectDTO> get(@PathVariable Long id){
        ProjectDTO projectDTO = projectService.getProject(id);
        
        return Result.success(projectDTO);
    }
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable long id) throws SQLException {
        
        projectService.delete(id);
        
        return Result.success("删除成功");
    }
    
    @PutMapping("/{id}")
    public Result<String> update(@RequestBody ProjectDTO projectDTO,
                         @PathVariable Long id){
        log.info("项目更新:{}", projectDTO);
        projectService.update(projectDTO,id);
        
        return Result.success("修改成功");
    }
    
    @PostMapping("/join")
    public Result<String> join(@RequestBody JoinProjectDTO joinProjectDTO) throws MyException {
        projectService.join(joinProjectDTO);
        return Result.success("成功报名，请等待审核");
    }
    
    
}