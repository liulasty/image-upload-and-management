package com.lz.Dao;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/03/16:55
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.pojo.entity.SportsImg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lz
 */
@Mapper
public interface SportsImgDao extends BaseMapper<SportsImg> {
    /**
     * 按类型和ID选择IMG SRC
     *
     * @param imgType IMG型
     * @param typeId  类型 ID
     *
     * @return {@code List<String>}
     */
    List<String> selectImgSrcByTypeAndId(@Param("imgType") String imgType, @Param("typeId") Long typeId);

    /**
     * 按 typeid 删除
     * 按 typeid 删除
     *
     * @param typeId 类型 ID
     *
     * @return int
     */
    @Delete("DELETE FROM lz_sports.sportsimg WHERE typeid = #{typeId}")
    int deleteByTypeId(@Param("typeId") String typeId);

    /**
     * 按类型 ID 和 SRC 删除
     *
     * @param typeId 类型 ID
     * @param ossUrl OSS URL
     */
    @Delete("DELETE FROM lz_sports.sportsimg WHERE typeid = #{typeId} && " +
            "ImgSrc = #{src}")
    void deleteByTypeIdAndSrc(@Param("typeId")Long typeId,
                              @Param("src") String ossUrl);
}