package com.lz.service.Impl;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/05/16:42
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.Dao.AthleteDao;
import com.lz.Dao.UserDao;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.dto.UserLoginDTO;
import com.lz.pojo.entity.Athlete;
import com.lz.pojo.entity.User;
import com.lz.pojo.result.PageResult;
import com.lz.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author lz
 */
@Service
public class UserServiceImpl implements com.lz.service.UserService {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private AthleteDao athleteDao;
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        User user;
        if(userLoginDTO.getUsername().contains("@")){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Email",userLoginDTO.getUsername());
            queryWrapper.eq("Password",userLoginDTO.getPassword());
            user = userDao.selectOne(queryWrapper);
        }else {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Username",userLoginDTO.getUsername());
            queryWrapper.eq("Password",userLoginDTO.getPassword());
            user = userDao.selectOne(queryWrapper);
        }
        
        return user;
    }

    /**
     * 列表
     *
     * @param listDto 列出 DTO
     *
     * @return {@code PageResult}
     */
    @Override
    public PageResult list(EventListDto listDto) {

        System.out.println("listDto:" + listDto);
        List<UserVO> list = userDao.selectAllAndState(listDto);

        int totalUserCount = userDao.getTotalUserCount(listDto);


        return new PageResult(totalUserCount,list);
    }

    @Override
    public void delete(String id) throws SQLException {
        int delete = userDao.deleteById(id);
        System.out.println("delete:" + delete);
        if (delete == 0) {
            throw new SQLException("删除失败，请刷新重试");
        }
        

        
        
    }

    @Override
    public void examinePlayer(String id) {
        User user = new User();
        user.setUserId(Long.valueOf(id));
        user.setUserType("运动员");
        userDao.updateById(user);

        Athlete athlete =
                Athlete.builder().agreeTime(new Date()).state("成功").build();

        QueryWrapper<Athlete> whereWrapper = new QueryWrapper<>();
        whereWrapper.eq("UserId", Long.valueOf(id));
        
        athleteDao.update(athlete,whereWrapper);
    }
}