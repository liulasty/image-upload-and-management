package com.lz.service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/14/11:29
 * @Description:
 */
public interface SportsImgService {
    /**
     * 选择IMG
     *
     * @param id   编号
     * @param type 类型
     *
     * @return {@code String[]}
     */
    public List<String> selectImg(Long id, String type);
}