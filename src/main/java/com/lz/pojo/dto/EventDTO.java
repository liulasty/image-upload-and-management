package com.lz.pojo.dto;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/10/29/11:53
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author lz
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO implements Serializable {
    private String[] date;
    private String name;
    private String fee;
    private String type;
    private ImgDTO[] imageUrls;
    private ImgDTO[] deleteImagesUrls;
    
}