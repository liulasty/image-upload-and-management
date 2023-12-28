package com.lz.service;

import com.lz.pojo.dto.RegistrationDTO;
import com.lz.pojo.result.PageResult;

import java.util.Date;

/**
 * 注册服务
 * Created with IntelliJ IDEA.
 *
 * @author lz
 
 * @date 2023/12/06
 */
public interface RegistrationService {

    /**
     * 列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param name        名字
     * @param status      地位
     * @param date        日期
     *
     * @return {@code PageResult}
     */
    PageResult list(int currentPage, int pageSize,
                                     String name, String status, Date date);

    /**
     * 删除参赛记录
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 同意参加
     *
     * @param id 编号
     */
    void attend(Long id);
}