package com.lz.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/05/16:36
 * @Description:
 */

import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.dto.UserLoginDTO;
import com.lz.pojo.entity.User;
import com.lz.pojo.result.PageResult;

import java.sql.SQLException;

/**
 * 用户服务
 *
 * @author lz
 * @date 2023/11/05
 */
public interface UserService {
    public User login(UserLoginDTO userLoginDTO);


    /**
     * 列表
     *
     * @param listDto 列出 DTO
     *
     * @return {@code PageResult}
     */
    PageResult list(EventListDto listDto);

    /**
     * 删除
     *
     * @param id 编号
     */
    void delete(String id) throws SQLException;

    /**
     * 检查播放器
     *
     * @param id 编号
     */
    void examinePlayer(String id);
}