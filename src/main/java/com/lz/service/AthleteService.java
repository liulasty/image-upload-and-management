package com.lz.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/16:56
 * @Description:
 */

import com.lz.pojo.dto.AthleteDTO;

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
}