package com.lz.pojo.result;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/01/9:14
 * @Description:
 */

import com.lz.pojo.result.chart.OrderData;
import com.lz.pojo.result.chart.TableData;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lz
 * Serializable
 * 表示一个类可以被序列化。序列化是将对象转换为字节序列的过程，以便将其保存到文件、数据库或通过网络传输。
 * 反序列化则是将字节序列重新转换为对象的过程。
 * 这意味着类的对象可以被转换为字节流，以便在不同的环境中进行存储或传输。
 * 这在许多情况下非常有用，比如在分布式系统中传递对象、将对象持久化到磁盘上，或者通过网络传输对象数据。
 */
@Data
public class MainPageData implements Serializable {
    private TableData[] tableData;
    private OrderData orderData;
    
}