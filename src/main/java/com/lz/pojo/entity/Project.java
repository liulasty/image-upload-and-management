package com.lz.pojo.entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/22:37
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author lz
 */
@Data
@Builder
@TableName("eventitem")
public class Project {
    @TableId(value = "ItemID", type = IdType.AUTO)
    private Long ItemID;
    private Long   EventID;
    private String ItemName;
}