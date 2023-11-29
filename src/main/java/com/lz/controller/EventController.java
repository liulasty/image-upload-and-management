package com.lz.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/10/29/8:45
 * @Description:
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lz.Dao.EventDao;
import com.lz.pojo.dto.EventDTO;
import com.lz.pojo.dto.EventListDto;
import com.lz.pojo.entity.Event;
import com.lz.pojo.result.MainPageData;
import com.lz.pojo.result.PageResult;
import com.lz.pojo.result.Result;
import com.lz.pojo.result.chart.OrderData;
import com.lz.pojo.vo.EventVO;
import com.lz.service.EventService;
import com.lz.service.SportsImgService;
import com.lz.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lz
 */
@RestController
@RequestMapping("sports/event")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EventController {

    @Autowired
    private EventService eventService;
    
    @Autowired
    private SportsImgService sportsImgService;

    @PostMapping("/EventList")
    public String addEvent(@RequestBody EventDTO eventDTO) throws SQLIntegrityConstraintViolationException {

        System.out.println("eventDTO.getImageUrls():" + eventDTO.getImageUrls());
        String s = eventService.addEvent(eventDTO);
        return s;


    }

    @GetMapping("/page")
    public Result<PageResult> list(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String type,
                                   @RequestParam(required = false) String date,
                                   @RequestParam(defaultValue = "1") int currentPage,
                                   @RequestParam(defaultValue = "5") int pageSize) {

        EventListDto eventListDto = new EventListDto(name, type, date, currentPage, pageSize);

        try {
            
            System.out.println("eventListDto:" + eventListDto);
            PageResult list = eventService.list(eventListDto);

            return Result.success(list);
        } catch (Exception e) {
            e.printStackTrace();

            return Result.error("未知错误");
        }
    }

    @GetMapping("/mainPageData")
    public Result<MainPageData> mainPageDataResult() {
        try {
            MainPageData mainPageData = new MainPageData();
            mainPageData.setTableData(eventService.getNewTen());
            mainPageData.setOrderData(new OrderData());
            return Result.success(mainPageData);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("未知错误");
        }


    }

    /**
     * 选择
     *
     * @param eventId 事件 ID
     *
     * @return {@code Result<EventVO>}
     */
    @GetMapping("/{eventId}")
    public Result<EventVO> select(@PathVariable Long eventId) throws NullPointerException{

        try {
            Event event = eventService.getEventId(eventId);
            List<String> eventImg = sportsImgService.selectImg(event.getEventId(),
                                                               "活动图片");
            EventVO eventVO = EventVO.builder()
                    .name(event.getEventName())
                    .date(DataUtil.dateToString(event.getRegistrationStart()))
                    .fee(String.valueOf(event.getRegistrationFee()))
                    .type(event.getEligibility())
                    .imageUrls(eventImg)
                    .build();

            return Result.success(eventVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error("查询失败");
    }

    /**
     * 删除事件
     *
     * @param deleteEventId 删除事件 ID
     *
     * @return {@code Result}
     *
     * @throws SQLException SQLException
     */
    @DeleteMapping("/{deleteEventId}")
    public Result deleteEvent(@PathVariable String deleteEventId) throws SQLException {
        
        String flag = eventService.deleteEvent(deleteEventId);
        
        return Result.success(flag);
    }

    /**
     * 字符串到数据
     *
     * @param s s
     *
     * @return {@code Date}
     */
    public Date StringToData(String s) {

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    
    
    @PutMapping("/{eventId}")
    public Result update(@PathVariable String eventId,@RequestBody EventDTO eventDTO){
        eventService.update(eventId,eventDTO);
        
        return Result.success("修改成功");
    }


    /**
     * 事件类型
     *
     * @return {@code Result<String[]>}
     */
    @GetMapping("/eventType")
    public Result<String[]> eventType(){

        String[] types = eventService.getEventType();
        
        return Result.success(types);
    }
}