package com.lz.service;

import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.dto.ProjectDTO;
import com.lz.pojo.result.PageResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/20:21
 * @Description:
 */
public interface ProjectService {
    /**
     * 项目列表
     *
     * @param listDto 列出 DTO
     * @return
     */
    PageResult list(EventListDto listDto);

    /**
     * 添加项目
     *
     * @param projectDTO 项目 DTO
     */
    void add(ProjectDTO projectDTO);
}