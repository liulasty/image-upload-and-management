package com.lz.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/16:56
 * @Description:
 */

import com.lz.pojo.dto.AthleteDTO;
import com.lz.pojo.entity.Athlete;

/**
 * @author lz
 */
public interface AthleteService {
    

    /**
     * 运动员申请
     *
     * @return {@code String}
     */
    String add(AthleteDTO athleteDTO);

    /**
     * 查看申请
     *
     * @param id 编号
     *
     * @return {@code Athlete}
     */
    Athlete selectApply(Integer id);
}