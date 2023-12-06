package com.lz.Dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.pojo.dto.RegistrationDTO;
import com.lz.pojo.entity.Registration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 注册 DAO
 *
 * @author lz
 * @date 2023/12/06
 */
@Mapper
public interface RegistrationDao extends BaseMapper<Registration> {


    /**
     * 获取包含详细信息注册
     *
     * @param limit  限制
     * @param offset 抵消
     * @param name   名字
     * @param status 地位
     * @param date   日期
     *
     * @return {@code List<RegistrationDTO>}
     */
    List<RegistrationDTO> getRegistrationsWithDetails(@Param("limit") int limit, @Param(
            "offset") int offset, String name, String status, Date date);

    /**
     * 获取报名总数
     *
     * @param name   名字
     * @param status 地位
     * @param date   日期
     *
     * @return int
     */
    int getRegistrationsTotal(String name, String status, Date date);
}