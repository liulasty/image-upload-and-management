package com.lz.Dao;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/22:53
 * @Description:
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.entity.Event;
import com.lz.pojo.entity.Project;
import com.lz.pojo.vo.ProjectVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lz
 */
@Mapper
public interface ProjectDao extends BaseMapper<Project> {

    /**
     * 选择项目
     *
     * @param eventListDto 事件列表 DTO
     *
     * @return {@code List<ProjectVO>}
     */
    List<ProjectVO> selectProject(EventListDto eventListDto);

    /**
     * 选择项目总计
     *
     * @param listDto 列出 DTO
     *
     * @return long
     */
    long selectProjectTotal(EventListDto listDto);
    
    
    Long addProject(Project project);
}