package com.lz.Dao;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/10/30/9:10
 * @Description:
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.pojo.entity.Event;
import com.lz.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lz
 */
@Mapper
@Transactional
public interface EventDao extends BaseMapper<Event> {
    /**
     * INSERT 事件
     *
     * @param event 事件
     */
    void insertEvent(Event event);
    
    
    String[] selectEventName();
}