package com.lz.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/10/30/9:06
 * @Description:
 */


import com.lz.pojo.dto.EventDTO;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.entity.Event;
import com.lz.pojo.result.PageResult;
import com.lz.pojo.result.chart.TableData;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
 * @author lz
 */
public interface EventService {
    /**
     * 添加事件
     *
     * @param eventDTO 事件 DTO
     *
     * @return {@code String}
     *
     * @throws SQLIntegrityConstraintViolationException SQLColiction 约束冲突异常
     */
    public String addEvent(EventDTO eventDTO) throws SQLIntegrityConstraintViolationException;

    /**
     * 分页列表
     *
     * @param eventListDto 事件列表 DTO
     *
     * @return {@code PageResult}
     */
    public PageResult list(EventListDto eventListDto);

    /**
     * 获取新十
     *
     * @return {@code TableData[]}
     */
    public TableData[] getNewTen();


    /**
     * 获取事件 ID
     *
     * @param eventId 事件 ID
     *
     * @return {@code Event}
     */
    Event getEventId(Long eventId);

    /**
     * 删除事件
     *
     * @param eventId 事件 ID
     *
     * @return {@code String}
     */
    String deleteEvent(String eventId) throws SQLException;

    /**
     * 更新
     *
     * @param eventId  事件 ID
     * @param eventDTO 事件 DTO
     */
    void update(String eventId, EventDTO eventDTO);

    /**
     * 获取事件类型
     *
     * @return {@code String[]}
     */
    List<Map<Long,String>> getEventType();
}