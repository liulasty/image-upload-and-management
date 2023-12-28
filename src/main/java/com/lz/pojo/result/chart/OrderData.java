package com.lz.pojo.result.chart;
import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/01/9:57
 * @Description:
 */

import lombok.Data;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author lz
 */
@Data
public class OrderData<T> implements Serializable{
    private List<T> data = new ArrayList<>();
    private String[] date;
    
    public OrderData(){
        

        String[] date = {"20191001", "20191002", "20191003", "20191004",
                "20191005", "20191006", "20191007"};
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 获取前六天的日期 
        for (int i = 6; i >= 0; i--) {
            LocalDate sixDaysAgo = currentDate.minusDays(i);
            String formattedSixDaysAgo = sixDaysAgo.format(formatter);
            date[6-i]=formattedSixDaysAgo;
        }
        

        this.date = date;
    }
}