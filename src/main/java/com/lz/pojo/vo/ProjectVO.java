package com.lz.pojo.vo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/29/22:42
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lz
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectVO implements Serializable {
    private Long projectId;
    
    private Date start;
    
    private String name;
    
    private String eventName;
    
    private String state;
    
    
}