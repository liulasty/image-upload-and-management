package com.lz.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.entity.User;
import com.lz.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/08/8:46
 * @Description:
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 通过电子邮件选择
     *
     * @param email 电子邮件
     *
     * @return {@code User}
     */
    public User selectByEmail(String email);


    /**
     * 全选和状态
     *
     * @param listDto 列出 DTO
     *
     * @return {@code List<UserVO>}
     */
    public List<UserVO> selectAllAndState(EventListDto listDto);
    
   
    public int getTotalUserCount(EventListDto listDto);
}