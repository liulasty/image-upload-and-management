package com.lz.service.Impl;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/16:57
 * @Description:
 */

import com.lz.Dao.AthleteDao;
import com.lz.pojo.dto.AthleteDTO;
import com.lz.pojo.entity.Athlete;
import com.lz.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lz
 */
@Service
public class AthleteServiceImpl implements AthleteService {
   

    @Autowired
    private AthleteDao athleteDao;
    /**
     * 运动员申请
     *
     * @param athleteDTO
     *
     * @return {@code String}
     */
    @Override
    public String add(AthleteDTO athleteDTO) {
        Athlete athlete = Athlete.builder().age(String.valueOf(athleteDTO.getAge()))
                .contact(athleteDTO.getGender())
                .applyTime(new Date())
                .name(athleteDTO.getName())
                .state("申请中")
                .userId(athleteDTO.getUserId())
                .build();

        int insert = athleteDao.insert(athlete);
        return String.valueOf(insert);
    }
}