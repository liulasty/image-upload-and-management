package com.lz.pojo.entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/05/16:36
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author lz
 */
@Data
public class User {
    @TableId(value = "UserID",type = IdType.AUTO)
    private Long userId;
    @TableField("Username")
    private String userName;
    @TableField("Password")
    private String password;
    @TableField("UserType")
    private String userType;
    @TableField("Status")
    private String status;
    @TableField("Email")
    private String email;
    @TableField("registerTime")
    private Date registerTime;
}