package com.lz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lz.Dao.*;
import com.lz.Exception.MyException;
import com.lz.context.BaseContext;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.dto.ImgDTO;
import com.lz.pojo.dto.JoinProjectDTO;
import com.lz.pojo.dto.ProjectDTO;
import com.lz.pojo.entity.*;
import com.lz.pojo.result.PageResult;
import com.lz.pojo.vo.ProjectVO;
import com.lz.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 项目服务实施
 *
 * @author lz
 * @date 2023/12/06
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private SportsImgDao sportsImgDao;
    
    @Autowired
    private RegistrationDao registrationDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private AthleteDao  athleteDao;

    @Override
    public PageResult list(EventListDto listDto) {
        System.out.println("listDto:" + listDto);

        List<ProjectVO> projectVos = projectDao.selectProject(listDto);

        Long uid = BaseContext.getCurrentId();
        QueryWrapper<Athlete> athleteQW = Wrappers.query();
        athleteQW.eq("UserID", uid);
        Athlete athlete = athleteDao.selectOne(athleteQW);
        for (ProjectVO projectVo : projectVos) {
            QueryWrapper<Registration> queryWrapper = Wrappers.query();
            queryWrapper.eq("AthleteID", athlete.getAthleteId());
            queryWrapper.eq("ItemID",projectVo.getProjectId());
            queryWrapper.eq("EventID",projectVo.getEventId());

            Registration registration = registrationDao.selectOne(queryWrapper);

            if (registration == null) {
                projectVo.setIsJoin("未参加");
            }else {
                projectVo.setIsJoin(registration.getStatus());
            }
            

        }

        long total = projectDao.selectProjectTotal(listDto);

        return new PageResult(total, projectVos);

    }

    /**
     * 添加项目
     *
     * @param projectDTO 项目 DTO
     */
    @Override
    public void add(ProjectDTO projectDTO) {
        Project project =
                Project.builder()
                        .eventID(projectDTO.getEvent())
                        .itemName(projectDTO.getName())
                        .createTime(new Date())
                        .build();
        int insert = projectDao.insert(project);

        Long itemId = project.getItemID();

        System.out.println("itemID:" + itemId);

        if (projectDTO.getImageUrls().length > 0) {
            projectDTO.mapOssUrlToAddImage();

            sportsImgDao.addImageSrcs("项目图片", itemId, projectDTO.getAddImage());

            System.out.println("projectDTO.getAddImage():" + Arrays.toString(projectDTO.getAddImage()));
        }


    }

    @Override
    public void update(ProjectDTO projectDTO,Long id) {
        Project project =
                Project.builder().eventID(projectDTO.getEvent()).itemName(projectDTO.getName()).itemID(id).build();
        int i = projectDao.updateById(project);
        Long itemId = project.getItemID();
        projectDTO.mapOssUrlToAddImage();
        if (projectDTO.getDeleteImagesUrls().length > 0) {
            
            sportsImgDao.deleteByTypeIdAndImgSrcAndImgType(itemId,
                                                  projectDTO.getDeleteImage(),"项目图片");
        }

        for (ImgDTO imageUrl : projectDTO.getImageUrls()) {
            if (!"old".equals(imageUrl.getType())) {
                sportsImgDao.addImageSrc("项目图片",itemId, imageUrl.getOssUrl());
            }

        }
    }

    @Override
    public void delete(Long id) throws SQLException {

        QueryWrapper<Registration> RegistrationQW = Wrappers.query();
        RegistrationQW.eq("UserID", id);
        Registration registration = registrationDao.selectOne(RegistrationQW);
        if (registration == null){
            throw new SQLException("删除失败，请先删除相关报名记录");
        }
        int i = projectDao.deleteById(id);
        
        sportsImgDao.deleteByImgTypeAndTypeId("项目照片",id);
    }

    /**
     * 获取项目
     *
     * @return {@code ProjectDTO}
     */
    @Override
    public ProjectDTO getProject(Long id) {
        Project project = projectDao.selectById(id);

        List<String> list = sportsImgDao.selectImgSrcByTypeAndId("项目图片", id);
        String[] array = new String[list.size()];
        return ProjectDTO.builder().name(project.getItemName()).event(project.getEventID()).addImage(list.toArray(array)).build();
    }

    /**
     * 加入
     *
     * @param joinProjectDTO 加入 DTO 项目
     */
    @Override
    public void join(JoinProjectDTO joinProjectDTO) throws MyException {
        Long id = BaseContext.getCurrentId();

        User user = userDao.selectById(id);
        
        if(!Objects.equals(user.getUserType(), "运动员")){
            throw new MyException("请登录重试");
        }
        QueryWrapper<Athlete> queryWrapper = Wrappers.query();
        queryWrapper.eq("UserID", id);
        Athlete athlete = athleteDao.selectOne(queryWrapper);

        Registration registration = Registration.builder()
                .athleteId(athlete.getAthleteId())
                .eventId(joinProjectDTO.getEventId())
                .itemId(joinProjectDTO.getProjectId())
                .status("审核中")
                .time(new Date())
                .build();
        int insert = registrationDao.insert(registration);
        
        

    }
}