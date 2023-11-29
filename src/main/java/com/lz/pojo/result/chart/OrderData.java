package com.lz.pojo.result.chart;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/01/9:57
 * @Description:
 */

import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author lz
 */
@Data
public class OrderData implements Serializable{
    private List<MobilePhone> data = new ArrayList<>();
    private String[] date;
    
    public OrderData(){
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            MobilePhone mobilePhone = new MobilePhone();
            mobilePhone.setApple(random.nextInt(7901) + 100);
            mobilePhone.setVivo(random.nextInt(7901) + 100);
            mobilePhone.setOppo(random.nextInt(7901) + 100);
            mobilePhone.setMeizu(random.nextInt(7901) + 100);
            mobilePhone.setSamsung(random.nextInt(7901) + 100);
            mobilePhone.setXiaomi(random.nextInt(7901) + 100);
            data.add(mobilePhone);
        }
        String[] date = {"20191001", "20191002", "20191003", "20191004", 
                "20191005", "20191006", "20191007"};

        this.date = date;
    }
}

class MobilePhone implements Serializable{
    private int apple;
    private int vivo;
    private int oppo;
    private int meizu;
    private int samsung;
    private int xiaomi;

    public int getApple() {
        return apple;
    }

    public void setApple(int apple) {
        this.apple = apple;
    }

    public int getVivo() {
        return vivo;
    }

    public void setVivo(int vivo) {
        this.vivo = vivo;
    }

    public int getOppo() {
        return oppo;
    }

    public void setOppo(int oppo) {
        this.oppo = oppo;
    }

    public int getMeizu() {
        return meizu;
    }

    public void setMeizu(int meizu) {
        this.meizu = meizu;
    }

    public int getSamsung() {
        return samsung;
    }

    public void setSamsung(int samsung) {
        this.samsung = samsung;
    }

    public int getXiaomi() {
        return xiaomi;
    }

    public void setXiaomi(int xiaomi) {
        this.xiaomi = xiaomi;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "apple=" + apple +
                ", vivo=" + vivo +
                ", oppo=" + oppo +
                ", meizu=" + meizu +
                ", samsung=" + samsung +
                ", xiaomi=" + xiaomi +
                '}';
    }
}