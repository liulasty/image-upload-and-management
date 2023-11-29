package com.lz;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/10/31/8:00
 * @Description:
 */

import com.lz.Dao.EventDao;
import com.lz.Dao.SportsImgDao;
import com.lz.pojo.entity.Event;
import com.lz.pojo.entity.SportsImg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author lz
 */
@SpringBootTest
public class test {
    @Autowired
    private EventDao eventDao;
    
    @Autowired
    private SportsImgDao sportsImgDao;
    
   
    
    @Test
    public void addList(){
        List<Event> events = eventDao.selectList(null);
        
        events.forEach(event -> System.out.println("event:" + event));
        
        
    }
    
    @Test
    public void addImgSrc(){
        List<SportsImg> sportsImgs = sportsImgDao.selectList(null);
        
        sportsImgs.forEach(sportsImg -> System.out.println("sportsImg:" + sportsImg));
    }
}