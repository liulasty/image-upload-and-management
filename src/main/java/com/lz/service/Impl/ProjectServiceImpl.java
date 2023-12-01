package com.lz.service.Impl;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/20:21
 * @Description:
 */

import com.lz.Dao.ProjectDao;
import com.lz.Dao.SportsImgDao;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.dto.ImgDTO;
import com.lz.pojo.dto.ProjectDTO;
import com.lz.pojo.entity.Project;
import com.lz.pojo.entity.SportsImg;
import com.lz.pojo.result.PageResult;
import com.lz.pojo.vo.ProjectVO;
import com.lz.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author lz
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private ProjectDao projectDao;
    
    @Autowired
    private SportsImgDao sportsImgDao;
    
    @Override
    public PageResult list(EventListDto listDto) {
        System.out.println("listDto:" + listDto);

        List<ProjectVO> projectVOS = projectDao.selectProject(listDto);
        
        long total =  projectDao.selectProjectTotal(listDto);
        
        return new PageResult(total,projectVOS);

    }

    /**
     * 添加项目
     *
     * @param projectDTO 项目 DTO
     */
    @Override
    public void add(ProjectDTO projectDTO) {
        Project project =
                Project.builder().eventID(projectDTO.getEvent()).itemName(projectDTO.getName()).build();
        projectDao.insert(project);

        Long itemID = project.getItemID();

        System.out.println("itemID:" + itemID);

        projectDTO.mapOssUrlToAddImage();
        
        sportsImgDao.addImageSrc("项目图片",itemID,projectDTO.getAddImage());


        System.out.println("projectDTO.getAddImage():" + Arrays.toString(projectDTO.getAddImage()));
        
        
    }


}