package com.lz.service.impl;


import com.lz.Dao.AthleteDao;
import com.lz.Dao.EventDao;
import com.lz.Dao.ProjectDao;
import com.lz.Dao.RegistrationDao;
import com.lz.pojo.dto.RegistrationAndAthleteDTO;
import com.lz.pojo.dto.RegistrationDTO;
import com.lz.pojo.entity.Athlete;
import com.lz.pojo.entity.Event;
import com.lz.pojo.entity.Project;
import com.lz.pojo.entity.Registration;
import com.lz.pojo.result.PageResult;
import com.lz.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lz
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;
    
    @Autowired
    private AthleteDao athleteDao;
    
    @Autowired
    private ProjectDao projectDao;
    
    @Autowired
    private EventDao eventDao;

    /**
     * 列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param name        名字
     * @param status      地位
     * @param date        日期
     *
     * @return {@code PageResult}
     */
    @Override
    public PageResult list(int currentPage, int pageSize, String name,
                           String status, Date date) {
        System.out.println("date:" + date);
        int offset = (currentPage - 1) * pageSize;
        List<RegistrationDTO> registrations = 
                registrationDao.getRegistrationsWithDetails(pageSize, offset, name, status, date);
        int registrationsTotal = 
                registrationDao.getRegistrationsTotal(name, status, date);
        return new PageResult(registrationsTotal, registrations);
    }

    /**
     * 运动员查询参赛记录列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param name        名字
     * @param status      地位
     * @param date        字符串到数据
     * @param id          uid
     *
     * @return {@code PageResult}
     */
    @Override
    public PageResult listByPlayer(int currentPage, int pageSize, String name
            , String status, Date date, Long id) {

        System.out.println("date:" + date);
        int offset = (currentPage - 1) * pageSize;
        List<RegistrationDTO> registrations =
                registrationDao.getRegistrationsWithDetailsByPlayer(pageSize, offset, name, status, date, id);
        int registrationsTotal =
                registrationDao.getRegistrationsTotalByPlayer(name, status, date, id);
        return new PageResult(registrationsTotal, registrations);
    }

    /**
     * 删除参赛记录
     *
     * @param id 编号
     */
    @Override
    public void delete(Long id) {
        int i = registrationDao.deleteById(id);


    }

    /**
     * 同意参加
     *
     * @param id 编号
     */
    @Override
    public void attend(Long id) {

        Registration registration = Registration.builder().id(id).status("通过").build();
        int i = registrationDao.updateById(registration);

    }

    /**
     * 拒绝申请
     *
     * @param id 编号
     */
    @Override
    public void refuse(Long id) {
        Registration registration = Registration.builder().id(id).status(
                "不通过").build();
        int i = registrationDao.updateById(registration);
    }

    /**
     * 选择一项
     *
     * @param id 编号
     *
     * @return {@code RegistrationAndAthleteDTO}
     */
    @Override
    public RegistrationAndAthleteDTO selectOne(Long id) {
        Registration registration = registrationDao.selectById(id);
        Athlete athlete = athleteDao.selectById(registration.getAthleteId());
        Event event = eventDao.selectById(registration.getEventId());
        Project project = projectDao.selectById(registration.getItemId());

        return RegistrationAndAthleteDTO.builder()
                .id(registration.getId())
                .name(athlete.getName())
                .age(athlete.getAge())
                .gender(athlete.getGender())
                .contact(athlete.getContact())
                .athleteGrade(athlete.getGrade())
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .itemId(project.getItemID())
                .itemName(project.getItemName())
                .num(project.getAttendance())
                .maxNum(project.getMaxAttendance())
                .limitation(project.getLimitation())
                .grade(project.getGrade())
                .applyTime(registration.getTime())
                .build();
    }


}