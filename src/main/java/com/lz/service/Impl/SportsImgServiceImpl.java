package com.lz.service.Impl;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/14/11:31
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lz.Dao.SportsImgDao;
import com.lz.pojo.entity.Event;
import com.lz.pojo.entity.SportsImg;
import com.lz.service.SportsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sports IMG服务实现
 *
 * @author lz
 * @date 2023/11/14
 */
@Service
public class SportsImgServiceImpl implements SportsImgService {
    
    @Autowired
    private SportsImgDao sportsImgDao;

    /**
     * 选择IMG
     *
     * @param id
     * @param type
     *
     * @return {@code String[]}
     */
    @Override
    public List<String> selectImg(Long id, String type) {

        List<String> list = sportsImgDao.selectImgSrcByTypeAndId(type, id);
        
        return list;
    }
}