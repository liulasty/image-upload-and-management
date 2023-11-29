package com.lz.service.Impl;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/20:21
 * @Description:
 */

import com.lz.Dao.ProjectDao;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.result.PageResult;
import com.lz.pojo.vo.ProjectVO;
import com.lz.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lz
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private ProjectDao projectDao;
    @Override
    public PageResult list(EventListDto listDto) {
        System.out.println("listDto:" + listDto);
        listDto.setCurrentPage((listDto.getCurrentPage()-1)*listDto.getPageSize());

        List<ProjectVO> projectVOS = projectDao.selectProject(listDto);
        
        long total =  projectDao.selectProjectTotal(listDto);
        
        return new PageResult(total,projectVOS);

    }
}