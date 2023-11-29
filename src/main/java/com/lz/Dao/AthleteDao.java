package com.lz.Dao;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/14:43
 * @Description:
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.pojo.entity.Athlete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lz
 */
@Mapper
@Transactional
public interface AthleteDao extends BaseMapper<Athlete> {
    
}