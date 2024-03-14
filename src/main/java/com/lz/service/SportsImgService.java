package com.lz.service;

import com.lz.pojo.entity.SportsImg;

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
     * 查找IMGs
     *
     * @param id   编号
     * @param type 类型
     *
     * @return {@code String[]}
     */
    public List<String> selectImgs(Long id, String type);

    /**
     * 添加 图片src
     *
     * @param sportsImg 体育IMG
     */
    void addSrc(SportsImg sportsImg) throws Exception;

    /**
     * 查找 img
     *
     * @param userId 用户 ID
     * @param avatar 化身
     *
     * @return {@code SportsImg}
     */
    String selectImg(Long userId, String avatar);
}