package com.lz.pojo.vo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * VO项目
 *
 * @author lz
 * @date 2023/12/06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectVO implements Serializable {
    private Long projectId;
    
    private Date start;
    
    private String name;
    
    private Long eventId;
    private String eventName;
    
    private String state;
    
    private String isJoin;
    
}